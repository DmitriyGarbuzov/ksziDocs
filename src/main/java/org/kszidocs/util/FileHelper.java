package org.kszidocs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;

public class FileHelper {

    private static final Logger logger = LoggerFactory
            .getLogger(FileHelper.class);

    private static final String TMP_FILE_DIR = System.getProperty("user.dir") + "/tmp_files";

    private FileHelper() {

    }

    public static File createNewFile(String fileName) throws IOException, URISyntaxException {
        File parentDirectory = new File(TMP_FILE_DIR);
        File file = new File(parentDirectory, fileName);
        file.createNewFile();
        return file;
    }

    public static String writeToFile(MultipartFile file) {
        File targetFile = null;
        try {
            targetFile = createNewFile(file.getOriginalFilename());
            OutputStream outStream = null;
            outStream = new FileOutputStream(targetFile);
            outStream.write(file.getBytes());
        } catch (IOException | URISyntaxException e) {
            logger.error("Error while creating new file ", e);
            e.printStackTrace();
        }

        return targetFile.getPath();
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
