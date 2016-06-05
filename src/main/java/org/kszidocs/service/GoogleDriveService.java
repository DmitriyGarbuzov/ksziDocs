package org.kszidocs.service;

import org.kszidocs.service.cloud.GoogleDriveAPIHelper;
import org.kszidocs.util.FileHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GoogleDriveService {

    public String uploadDocument(MultipartFile file) {
        return GoogleDriveAPIHelper.uploadFile(FileHelper.writeToFile(file), file.getContentType(), "");
    }

    public List<String> searchDocuments(String text) {
        return GoogleDriveAPIHelper.searchFiles(text);
    }

    public void removeDocument(String selfHref) {
        GoogleDriveAPIHelper.removeFile(selfHref);
    }
}
