package org.kszidocs.repository;

import org.kszidocs.entity.DocumentsGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface DocumentsGroupRepository extends JpaRepository<DocumentsGroup,Long> {
    DocumentsGroup findOneByUuid(UUID uuid);

    @Modifying
    @javax.transaction.Transactional
    Long deleteByUuid(UUID uuid);
}
