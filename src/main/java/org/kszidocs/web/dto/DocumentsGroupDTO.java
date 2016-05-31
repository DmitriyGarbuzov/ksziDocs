package org.kszidocs.web.dto;

import java.util.List;
import java.util.UUID;

public class DocumentsGroupDTO {

    private UUID uuid;

    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DocumentsGroupDTO{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                '}';
    }
}
