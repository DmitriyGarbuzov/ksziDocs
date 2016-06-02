package org.kszidocs.service;

import org.kszidocs.entity.Document;
import org.kszidocs.repository.DocumentRepository;
import org.kszidocs.web.converter.DocumentConverter;
import org.kszidocs.web.converter.DocumentsGroupConverter;
import org.kszidocs.web.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentConverter converter;

    public List<Document> getAllDocumentsByGroupUuid(UUID groupUuid) {
        return null;
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
}
