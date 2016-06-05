package org.kszidocs.service.cloud;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;
import org.kszidocs.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleDriveAPIHelper {

    private static final String SHARED_FILE_LINK_GOOGLE_DRIVE = "https://docs.google.com/file/d/%s/edit?usp=drivesdk";

    private static final Logger logger = LoggerFactory
            .getLogger(GoogleDriveAPIHelper.class);

    private static final Drive client = GoogleDriveConnector.getClient();

    public static String uploadFile(String path, String mimeType, String description) {
        java.io.File content = new java.io.File(path);
        File body = new File();
        body.setName(content.getName());
        body.setDescription(description);
        body.setMimeType(mimeType);
        FileContent mediaContent = new FileContent(mimeType, content);
        try {
            File file = client.files().create(body, mediaContent).execute();
            insertReadPermissions(file.getId());
            FileHelper.removeFile(path);
            return String.format(SHARED_FILE_LINK_GOOGLE_DRIVE, file.getId());
        } catch (IOException e) {
            logger.error("Exception while uploading file to GOOGLE DRIVE", e);
            return null;
        }
    }

    public static void removeFile(String selfHref) {
        try {
            logger.debug("Remove file '{}'",getFileIdFromHref(selfHref));
            client.files().delete(getFileIdFromHref(selfHref)).execute();
        } catch (IOException e) {
            logger.error("Unable to remove file", e);
        }
    }

    private static String getFileIdFromHref(String selfHref) {
        return selfHref.split("/")[5];
    }

    private static Permission insertReadPermissions(String fileId) throws IOException {
        Permission newPermission = new Permission();
        newPermission.setType("anyone");
        newPermission.setRole("reader");
        return client.permissions().create(fileId, newPermission).execute();
    }

    public static List<String> searchFiles(String text) {
        try {
            return client
                    .files()
                    .list()
                    .setQ(text)
                    .execute().getFiles()
                    .stream()
                    .map(file -> String.format(SHARED_FILE_LINK_GOOGLE_DRIVE, file.getId()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Exception while searching files with query '" + text + "+'", e);
            return null;
        }
    }
}
