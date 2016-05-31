package org.kszidocs.repository;

import org.kszidocs.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findOneByUuid(UUID uuid);
}
