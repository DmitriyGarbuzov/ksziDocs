package org.kszidocs.web.converter;

import com.google.common.base.Converter;
import org.joda.time.DateTime;
import org.kszidocs.entity.DocumentsGroup;
import org.kszidocs.repository.DocumentsGroupRepository;
import org.kszidocs.web.dto.DocumentsGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentsGroupConverter extends Converter<DocumentsGroup, DocumentsGroupDTO> {

    @Autowired
    private DocumentsGroupRepository documentsGroupRepository;

    @Override
    protected DocumentsGroupDTO doForward(DocumentsGroup entity) {
        DocumentsGroupDTO dto = new DocumentsGroupDTO();
        dto.setUuid(entity.getUuid());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    protected DocumentsGroup doBackward(DocumentsGroupDTO dto) {
        DocumentsGroup entity = null;
        if (dto.getUuid() != null) {
            entity = documentsGroupRepository.findOneByUuid(dto.getUuid());
            if(dto.getName() != null) {
                entity = perfomUpdate(dto, entity); // this var comes from dto select
            }
        } else {
            entity = perfomNewEntity(dto);
        }
        return entity;
    }

    private DocumentsGroup perfomUpdate(DocumentsGroupDTO dto,DocumentsGroup oldEntity) {

        if(!dto.getName().equals(oldEntity.getName())) {
            oldEntity.setName(dto.getName());
        }
        if(!dto.getDescription().equals(oldEntity.getDescription())) {
            oldEntity.setDescription(dto.getDescription());
        }
        return oldEntity;
    }

    private DocumentsGroup perfomNewEntity(DocumentsGroupDTO dto) {
        DocumentsGroup entity = new DocumentsGroup();
        entity.setCreatedTs(DateTime.now());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
