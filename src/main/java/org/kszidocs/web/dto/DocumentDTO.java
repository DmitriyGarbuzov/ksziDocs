package org.kszidocs.web.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class DocumentDTO {

    private UUID uuid;

    private String title;

    private String description;

    private String fileName;

    private String selfHref;

    private DocumentsGroupDTO documentsGroupDTO;

    private MultipartFile file;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSelfHref() {
        return selfHref;
    }

    public void setSelfHref(String selfHref) {
        this.selfHref = selfHref;
    }

    public DocumentsGroupDTO getDocumentsGroupDTO() {
        return documentsGroupDTO;
    }

    public void setDocumentsGroupDTO(DocumentsGroupDTO documentsGroupDTO) {
        this.documentsGroupDTO = documentsGroupDTO;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                ", selfHref='" + selfHref + '\'' +
                ", documentsGroupDTO=" + documentsGroupDTO +
                '}';
    }
}
