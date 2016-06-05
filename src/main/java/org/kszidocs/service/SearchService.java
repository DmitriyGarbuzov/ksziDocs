package org.kszidocs.service;

import org.kszidocs.entity.Document;
import org.kszidocs.repository.DocumentRepository;
import org.kszidocs.service.cloud.GoogleDriveAPIHelper;
import org.kszidocs.web.converter.DocumentConverter;
import org.kszidocs.web.dto.DocumentDTO;
import org.kszidocs.web.dto.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private static final Logger logger = LoggerFactory
            .getLogger(SearchService.class);

    @Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentConverter converter;

    public static final int SEARCH_BY_SUBJECT = 1;
    public static final int SEARCH_BY_KEYS = 2;
    public static final int SEARCH_BY_TEXT = 3;

    public List<DocumentDTO> search(SearchDTO dto) {
        return searchDocuments(dto)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    private List<Document> searchDocuments(SearchDTO dto) {
        List<Document> result = null;
        if (dto.getSearchType() == SEARCH_BY_SUBJECT) {
            result = searchBySubject(dto.getSearchText());
        } else if (dto.getSearchType() == SEARCH_BY_KEYS) {
            result = searchByKeys(dto.getSearchText());
        } else if (dto.getSearchType() == SEARCH_BY_TEXT) {
            result = searchByText(dto.getSearchText());
        }
        return result;
    }

    private List<Document> searchBySubject(String text) {
        return documentService.searchByTitle(text);
    }

    private List<Document> searchByKeys(String text) {
        return documentService.searchByDescription(text);
    }

    private List<Document> searchByText(String text) {
        List<String> result = googleDriveService.searchDocuments(text);
        return documentRepository
                .findAll()
                .stream()
                .filter(document -> result.contains(document.getSelfHref()))
                .collect(Collectors.toList());
    }
}
