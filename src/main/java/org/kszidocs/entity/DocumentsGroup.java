package org.kszidocs.entity;

import org.joda.time.DateTime;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "documents_group")
public class DocumentsGroup extends BaseEntity {

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

    @Column(name = "name", nullable = false)
    private String name;

    @Column (name = "description", nullable = true)
    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DocumentsGroup{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
