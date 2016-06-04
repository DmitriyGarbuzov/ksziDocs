package org.kszidocs.util;

import org.kszidocs.service.cloud.GoogleDriveAPIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileHelper {

    private static final Logger logger = LoggerFactory
            .getLogger(FileHelper.class);

    private static final String TMP_FILE_DIR = "tmp_files/";

    private FileHelper() {

    }

    public static File createNewFile(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
        return file;
    }

    public static String writeToFile(MultipartFile file) {
        String path = TMP_FILE_DIR + file.getOriginalFilename();
        try {
            File targetFile = createNewFile(path);
            OutputStream outStream = null;
            outStream = new FileOutputStream(targetFile);
            outStream.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static void removeFile(String path) {
        File file = new File(path);
        boolean success = file.delete();
        if (!success) {
            logger.error("Unable to remove tmp file '{}'", path);
        } else {
            logger.debug("Success to remove tmp file '{}'", path);
        }
    }
}
