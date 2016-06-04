package org.kszidocs.util;

import org.kszidocs.service.cloud.GoogleDriveAPIHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class FileHelper {

    private static final Logger logger = LoggerFactory
            .getLogger(FileHelper.class);

    private static final String TMP_FILE_DIR = "tmp_files/";

    private FileHelper() {

    }

    public static File createNewFile(String fileName) throws IOException, URISyntaxException {
        URL url = FileHelper.class.getResource(TMP_FILE_DIR);
        File parentDirectory = new File(new URI(url.toString()));
        File file = new File(parentDirectory, fileName);
        file.createNewFile();
        return file;
    }

    public static String writeToFile(MultipartFile file) {
        String path = getPath(file.getOriginalFilename());
        try {
            File targetFile = createNewFile(file.getOriginalFilename());
            OutputStream outStream = null;
            outStream = new FileOutputStream(targetFile);
            outStream.write(file.getBytes());
        } catch (IOException | URISyntaxException e) {
            logger.error("Error while creating new file ", e);
            e.printStackTrace();
        }
        return path;
    }

    private static String getPath(String fileName) {
        return FileHelper.class.getClassLoader().getResource(TMP_FILE_DIR + fileName).getFile();
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
