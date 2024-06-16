package com.example.legaldocassistant.controller;

import com.example.legaldocassistant.model.Document;
import com.example.legaldocassistant.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        Document savedDocument = documentService.saveDocument(document);
        return ResponseEntity.ok(savedDocument);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        Optional<Document> document = documentService.getDocumentById(id);
        return document.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long id, @RequestBody Document documentDetails) {
        Optional<Document> documentOptional = documentService.getDocumentById(id);
        if (!documentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Document document = documentOptional.get();
        document.setDocumentType(documentDetails.getDocumentType());
        document.setPartyOne(documentDetails.getPartyOne());
        document.setPartyTwo(documentDetails.getPartyTwo());
        document.setAgreementTerms(documentDetails.getAgreementTerms());

        Document updatedDocument = documentService.saveDocument(document);
        return ResponseEntity.ok(updatedDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
