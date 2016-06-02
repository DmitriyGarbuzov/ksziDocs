package org.kszidocs.entity;

import org.joda.time.DateTime;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name ="documents")
public class Document extends BaseEntity{

    @Type(type = "pg-uuid")
    @Column(name = "uuid", unique = true, nullable = false)
    private UUID uuid = UUID.randomUUID();

    @CreatedDate
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "created_ts", nullable = false)
    private DateTime createdTs;

    @LastModifiedDate
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "modified_ts")
    private DateTime modifiedTs;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "self_href", nullable = false)
    private String selfHref;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private DocumentsGroup group;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public DateTime getCreatedTs() {
        return createdTs;
    }

    public void setCreatedTs(DateTime createdTs) {
        this.createdTs = createdTs;
    }

    public DateTime getModifiedTs() {
        return modifiedTs;
    }

    public void setModifiedTs(DateTime modifiedTs) {
        this.modifiedTs = modifiedTs;
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

    public DocumentsGroup getGroup() {
        return group;
    }

    public void setGroup(DocumentsGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Document{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                ", selfHref='" + selfHref + '\'' +
                ", group=" + group +
                '}';
    }
}
