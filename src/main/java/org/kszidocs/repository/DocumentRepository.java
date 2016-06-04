package org.kszidocs.repository;

import org.kszidocs.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document findOneByUuid(UUID uuid);

    List<Document> findAllByGroupId(Long id);

    @Modifying
    @javax.transaction.Transactional
    Long deleteByUuid(UUID uuid);

    @Modifying
    @javax.transaction.Transactional
    Long deleteByGroupId(Long id);
}
