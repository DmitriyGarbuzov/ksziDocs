package org.kszidocs.web.dto;

import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public class DocumentDTO {

    private UUID uuid;

    private String title;

    private String description;

    private String fileName;

    private String selfHref;

    private String createdTs;

    private MultipartFile file;

    private DocumentsGroupDTO group;

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

    public DocumentsGroupDTO getGroup() {
        return group;
    }

    public void setGroup(DocumentsGroupDTO group) {
        this.group = group;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(String createdTs) {
        this.createdTs = createdTs;
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
                ", documentsGroupDTO=" + group +
                ", createdTs=" + createdTs +
                ", file=" + file +
                '}';
    }
}
