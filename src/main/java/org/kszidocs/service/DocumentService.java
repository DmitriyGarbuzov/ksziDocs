package org.kszidocs.service;

import org.kszidocs.entity.Document;
import org.kszidocs.entity.DocumentsGroup;
import org.kszidocs.repository.DocumentRepository;
import org.kszidocs.repository.DocumentsGroupRepository;
import org.kszidocs.web.converter.DocumentConverter;
import org.kszidocs.web.converter.DocumentsGroupConverter;
import org.kszidocs.web.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentConverter converter;

    @Autowired
    private DocumentsGroupRepository groupRepository;

    @Autowired
    private GoogleDriveService googleDriveService;

    public List<DocumentDTO> getAllDocumentsByGroupUuid(UUID groupUuid) {
        DocumentsGroup group = groupRepository.findOneByUuid(groupUuid);
        return documentRepository
                .findAllByGroupId(group.getId())
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public DocumentDTO getDocument(UUID uuid) {
        return Optional.ofNullable(
                documentRepository.findOneByUuid(uuid)
        )
                .map(converter::convert)
                .orElse(null);
    }

    public DocumentDTO saveDocument(DocumentDTO dto) {
        return Optional.ofNullable(
                documentRepository.save(converter.reverse().convert(dto))
        )
                .map(converter::convert)
                .orElse(null);
    }

    public void deleteDocument(UUID uuid) {
        Document document = documentRepository.findOneByUuid(uuid);
        String selfHref = document.getSelfHref();
        documentRepository.deleteByUuid(uuid);
        if (!selfHref.isEmpty()) {
            googleDriveService.removeDocument(selfHref);
        }
    }

    public void deleteAllDocumentsByGroupUuid(UUID uuid) {
        DocumentsGroup group = groupRepository.findOneByUuid(uuid);
        documentRepository.deleteByGroupId(group.getId());
    }

    public List<Document> searchByTitle(String text) {
        return documentRepository
                .findAll()
                .stream()
                .filter(document -> document.getTitle().toLowerCase().contains(text.toLowerCase())
                        || document.getTitle().toLowerCase().equals(text.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Document> searchByDescription(String text) {
        return documentRepository
                .findAll()
                .stream()
                .filter(document -> document.getDescription().toLowerCase().contains(text.toLowerCase())
                        || document.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }
}
