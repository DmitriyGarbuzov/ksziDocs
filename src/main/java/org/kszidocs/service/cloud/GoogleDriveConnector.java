package org.kszidocs.service.cloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

public class GoogleDriveConnector {

    private static final Logger logger = LoggerFactory
            .getLogger(GoogleDriveConnector.class);

    private static final String APPLICATION_NAME = "kszidocs";

    private static final String JSON_FILE_PATH = "/cloud_creds.json";

    private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static Drive client;

    private static String IDENTITY;

    private static String CREDENTIALS;

    static {
        ObjectMapper mapper = new ObjectMapper();
        ServiceAccountCredentials credentials = null;
        try {
            credentials =  mapper.readValue(
                    GoogleDriveConnector.class.getResourceAsStream(JSON_FILE_PATH),
                    ServiceAccountCredentials.class
            );
        } catch (IOException e) {
            logger.debug("Can't load credentials for Google Drive",e);
            e.printStackTrace();
        }
        IDENTITY = credentials.getClient_email();
        CREDENTIALS = credentials.getPrivate_key();
        logger.debug("Success loaded credentials for Google Drive, identity '{}'",IDENTITY);
    }

    public static synchronized Drive getClient() {
        if(client == null) {
            client = connect();
        }
        return client;
    }

    private static Drive connect() {
        Credential credential = getGoogleCredentials();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    private static GoogleCredential getGoogleCredentials() {
        try {
            return new GoogleCredential.Builder()
                    .setTransport(HTTP_TRANSPORT)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(IDENTITY)
                    .setServiceAccountPrivateKey(createKey(CREDENTIALS))
                    .setServiceAccountScopes(SCOPES)
                    .build();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | Base64DecodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private  static PrivateKey createKey(String credential) throws NoSuchAlgorithmException, InvalidKeySpecException, Base64DecodingException {
        return KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(formatCredentials(credential))));
    }

    private static String formatCredentials(String cred) {
        return cred.replaceAll("\n", "")
                .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                .replaceAll("-----END PRIVATE KEY-----", "");
    }
}
