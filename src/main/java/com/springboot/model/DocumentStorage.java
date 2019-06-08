package com.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document_storage")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentStorage {

    @Id
    @GeneratedValue
    private Integer id;

    private String documentName;
    private String encryptionDocumentName;
    private String documentUrl;
}
