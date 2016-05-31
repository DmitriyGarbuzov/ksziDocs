package org.kszidocs.repository;

import org.kszidocs.entity.DocumentsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentsGroupRepository extends JpaRepository<DocumentsGroup,Long> {
    DocumentsGroup findOneByUuid(UUID uuid);
}
