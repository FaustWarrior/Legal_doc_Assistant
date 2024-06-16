// DocumentRepository.java
package com.example.legaldocassistant.repository;

import com.example.legaldocassistant.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    // Add custom query methods if needed
}
