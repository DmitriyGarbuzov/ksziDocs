package org.kszidocs.service;

import org.kszidocs.repository.DocumentsGroupRepository;
import org.kszidocs.web.dto.DocumentsGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentsGroupService {

    @Autowired
    private DocumentsGroupRepository documentsGroupRepository;

    public List<DocumentsGroupDTO> getAllGroups() {
        return null;
    }

    public DocumentsGroupDTO getGroup(UUID groupUuid) {
        return null;
    }

}
