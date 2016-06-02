package org.kszidocs.service;

import org.kszidocs.repository.DocumentsGroupRepository;
import org.kszidocs.web.converter.DocumentsGroupConverter;
import org.kszidocs.web.dto.DocumentsGroupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentsGroupService {

    @Autowired
    private DocumentsGroupRepository documentsGroupRepository;

    @Autowired
    private DocumentsGroupConverter converter;

    public List<DocumentsGroupDTO> getAllGroups() {
        return documentsGroupRepository
                .findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public DocumentsGroupDTO getGroup(UUID uuid) {
        return Optional.ofNullable(
                documentsGroupRepository.findOneByUuid(uuid)
        )
                .map(converter::convert)
                .orElse(null);
    }

    public DocumentsGroupDTO saveGroup(DocumentsGroupDTO dto) {
        return Optional.ofNullable(
                documentsGroupRepository.save(converter.reverse().convert(dto))
        )
                .map(converter::convert)
                .orElse(null);
    }

}
