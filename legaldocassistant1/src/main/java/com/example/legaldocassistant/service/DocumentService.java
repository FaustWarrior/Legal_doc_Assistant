package com.example.legaldocassistant.service;

import com.example.legaldocassistant.model.Document;
import com.example.legaldocassistant.repository.DocumentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }
    
    public Optional<Document> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    // Add more methods as per your application's requirements
}
