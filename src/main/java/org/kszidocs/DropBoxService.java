package org.kszidocs;

import com.dropbox.core.*;

import java.io.File;
import java.util.Locale;

public class DropBoxService {

    private static final String APP_KEY = "yugt30bmu00kqil";
    private static final String APP_SECRET = "fakgouskk78mjia";

    private static final String access_token = "5m7mQqmL3gAAAAAAAAAAC8fur7Q1SxgwSbsMGoveH2cxmdri8KdDoBvnaRE_LUTG";

    public static void main(String[] args) {
        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());
        DbxClient client = new DbxClient(config,access_token);
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
