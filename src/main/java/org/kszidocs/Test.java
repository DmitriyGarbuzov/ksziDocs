package org.kszidocs;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.kszidocs.service.cloud.GoogleDriveConnector;
import com.google.api.services.drive.model.File;

import java.io.IOException;
import java.util.List;

/**
 * Created by Администратор on 04.06.2016.
 */
public class Test {
    public static void main(String[] args) {

        Drive client = GoogleDriveConnector.getClient();
        List<File> result= null;
        try {
           result = client.files().list().execute().getFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("result "+ result);
    }


}
