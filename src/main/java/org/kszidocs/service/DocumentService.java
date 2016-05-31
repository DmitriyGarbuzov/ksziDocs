package org.kszidocs.service;

import org.kszidocs.entity.Document;
import org.kszidocs.repository.DocumentRepository;
import org.kszidocs.web.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocumentsByGroupUuid(UUID groupUuid) {
        return null;
    }

    public DocumentDTO getDocument(UUID docUuid) {
        return null;
    }
}
