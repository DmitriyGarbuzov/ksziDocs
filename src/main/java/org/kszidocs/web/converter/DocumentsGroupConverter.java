package org.kszidocs.web.converter;

import com.google.common.base.Converter;
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
    protected DocumentsGroupDTO doForward(DocumentsGroup documentsGroup) {
        DocumentsGroupDTO dto = new DocumentsGroupDTO();
        dto.setUuid(documentsGroup.getUuid());
        dto.setName(documentsGroup.getName());
        return dto;
    }

    @Override
    protected DocumentsGroup doBackward(DocumentsGroupDTO documentsGroupDTO) {
        DocumentsGroup entity = null;
        if(documentsGroupDTO.getUuid()!=null) {
            entity = documentsGroupRepository.findOneByUuid(documentsGroupDTO.getUuid());
        }
        return entity;
    }
}
