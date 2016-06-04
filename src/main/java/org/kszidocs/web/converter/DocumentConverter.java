package org.kszidocs.web.converter;

import com.google.common.base.Converter;
import org.joda.time.DateTime;
import org.kszidocs.entity.Document;
import org.kszidocs.repository.DocumentRepository;
import org.kszidocs.service.GoogleDriveService;
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

    @Autowired
    GoogleDriveService googleDriveService;

    @Override
    protected DocumentDTO doForward(Document entity) {
        DocumentDTO dto = new DocumentDTO();

        dto.setUuid(entity.getUuid());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setFileName(entity.getFileName());
        dto.setSelfHref(entity.getSelfHref());
        dto.setGroup(
                Optional.ofNullable(entity.getGroup())
                        .map(documentsGroupConverter::convert)
                        .orElse(null)
        );
        return dto;
    }

    @Override
    protected Document doBackward(DocumentDTO dto) {
        Document entity = null;
        if (dto.getUuid() != null) {
            entity = documentRepository.findOneByUuid(dto.getUuid());
            entity = perfomUpdate(dto,entity);
        } else {
            entity = perfomNewEntity(dto);
        }
        return entity;
    }

    private Document perfomUpdate(DocumentDTO dto,Document oldEntity) {
        if(!dto.getTitle().equals(oldEntity.getTitle())) {
            oldEntity.setTitle(dto.getTitle());
        }
        if(!dto.getDescription().equals(oldEntity.getDescription())) {
            oldEntity.setDescription(dto.getDescription());
        }
        if(!dto.getGroup().getUuid().equals(oldEntity.getGroup().getUuid())) {
            oldEntity.setGroup(Optional.ofNullable(dto.getGroup()).map(documentsGroupConverter.reverse()::convert).orElse(null));
        }
        if(!dto.getFile().getOriginalFilename().equals(oldEntity.getFileName())) {
            googleDriveService.removeDocument(oldEntity.getFileName());
            oldEntity.setFileName(dto.getFile().getOriginalFilename());
            oldEntity.setSelfHref(googleDriveService.uploadDocument(dto.getFile()));
        }
        return oldEntity;
    }

    private Document perfomNewEntity(DocumentDTO dto) {
        Document entity = new Document();
        entity.setCreatedTs(DateTime.now());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setGroup(Optional.ofNullable(dto.getGroup()).map(documentsGroupConverter.reverse()::convert).orElse(null));
        entity.setFileName(dto.getFile().getOriginalFilename());
        entity.setSelfHref(googleDriveService.uploadDocument(dto.getFile()));
        return entity;
    }
}
