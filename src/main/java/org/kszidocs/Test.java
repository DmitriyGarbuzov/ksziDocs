//package org.kszidocs;
//
//import com.google.api.client.http.FileContent;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.model.FileList;
//import com.google.api.services.drive.model.Permission;
//import org.kszidocs.service.cloud.GoogleDriveConnector;
//import com.google.api.services.drive.model.File;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.regex.Pattern;
//
///**
// * Created by Администратор on 04.06.2016.
// */
//public class Test {
//
//    private static final String TMP_FILE_DIR = System.getProperty("user.dir") + "/tmp_files";
//
//    public static void main(String[] args) {
//
//        Drive client = GoogleDriveConnector.getClient();
//        List<File> result= null;
//        try {
//           result = client.files().list().execute().getFiles();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//
