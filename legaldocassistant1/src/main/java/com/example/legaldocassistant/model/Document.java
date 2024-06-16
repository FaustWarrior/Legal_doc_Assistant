package com.example.legaldocassistant.model;

import javax.persistence.*;

@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentType;
    private String partyOne;
    private String partyTwo;
    private String agreementTerms;

    // Default constructor
    public Document() {}

    // Parameterized constructor
    public Document(String documentType, String partyOne, String partyTwo, String agreementTerms) {
        this.documentType = documentType;
        this.partyOne = partyOne;
        this.partyTwo = partyTwo;
        this.agreementTerms = agreementTerms;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getPartyOne() {
        return partyOne;
    }

    public void setPartyOne(String partyOne) {
        this.partyOne = partyOne;
    }

    public String getPartyTwo() {
        return partyTwo;
    }

    public void setPartyTwo(String partyTwo) {
        this.partyTwo = partyTwo;
    }

    public String getAgreementTerms() {
        return agreementTerms;
    }

    public void setAgreementTerms(String agreementTerms) {
        this.agreementTerms = agreementTerms;
    }
}
