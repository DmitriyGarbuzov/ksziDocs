package org.kszidocs.service;

import org.kszidocs.entity.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class GoogleDriveService {

    public String uploadDocument(MultipartFile file) {
        return "vk.com";
    }

    public List<Document> searchDocuments(String searchText) {
        return null;
    }

    public void deleteDocument(String selfHref) {

    }
}
