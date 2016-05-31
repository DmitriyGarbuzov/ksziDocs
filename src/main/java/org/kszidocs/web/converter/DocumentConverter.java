package org.kszidocs.web.converter;

import com.google.common.base.Converter;
import org.kszidocs.entity.Document;
import org.kszidocs.repository.DocumentRepository;
import org.kszidocs.web.dto.DocumentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DocumentConverter extends Converter<Document, DocumentDTO> {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    DocumentsGroupConverter documentsGroupConverter;

    @Override
    protected DocumentDTO doForward(Document entity) {
        DocumentDTO dto = new DocumentDTO();

        dto.setUuid(entity.getUuid());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setFileName(entity.getFileName());
        dto.setSelfHref(entity.getSelfHref());
        dto.setDocumentsGroupDTO(
                Optional.ofNullable(entity.getGroup())
                        .map(documentsGroupConverter::convert)
                        .orElse(null)
        );
        return dto;
    }

    @Override
    protected Document doBackward(DocumentDTO dto) {
        Document entity = null;
        if (dto != null && dto.getUuid() != null) {
            entity = documentRepository.findOneByUuid(dto.getUuid());
        }
        return entity;
    }
}
