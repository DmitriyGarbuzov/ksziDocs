package org.kszidocs;

import com.dropbox.core.*;

import java.io.File;
import java.util.Locale;

public class DropBoxService {

    private static final String APP_KEY = "yugt30bmu00kqil";
    private static final String APP_SECRET = "fakgouskk78mjia";

    private static final String asd = "5m7mQqmL3gAAAAAAAAAACSoaP0x-DxYqXtrGO7IAoRo";

    public static void main(String[] args) {
        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
        String authorizeUrl = webAuth.start();

        DbxAuthFinish authFinish = null;
        try {
            authFinish = webAuth.finish(asd);
        } catch (DbxException e) {
            e.printStackTrace();
        }
        String accessToken = authFinish.accessToken;
        DbxClient client = new DbxClient(config, accessToken);
        try {
            System.out.println("Linked account: " + client.getAccountInfo().displayName);
        } catch (DbxException e) {
            e.printStackTrace();
        }
//        File inputFile = new File("working-draft.txt");
//        try {
//            DbxEntry.File uploadedFile = client.uploadFile("/magnum-opus.txt",
//                    DbxWriteMode.add(), inputFile.length(), inputStream);
//
//            client.searchFileAndFolderNames()
//            System.out.println("Uploaded: " + uploadedFile.toString());
//        } finally {
//            inputStream.close();
//        }
    }
}
