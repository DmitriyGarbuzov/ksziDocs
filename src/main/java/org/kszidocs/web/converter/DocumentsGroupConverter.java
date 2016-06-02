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
        return dto;
    }

    @Override
    protected DocumentsGroup doBackward(DocumentsGroupDTO dto) {
        DocumentsGroup entity = null;
        if (dto.getUuid() != null) {
            entity = documentsGroupRepository.findOneByUuid(dto.getUuid());
        } else {
            entity = new DocumentsGroup();
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setCreatedTs(DateTime.now());
        }
        return entity;
    }
}
