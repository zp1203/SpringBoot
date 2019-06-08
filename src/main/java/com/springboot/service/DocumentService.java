package com.springboot.service;

import com.springboot.dao.DocumentStorageRepository;
import com.springboot.model.DocumentStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentStorageRepository documentStorageRepository;

    public void saveDocument(String fileName,String fileUrl){
        DocumentStorage documentStorage = new DocumentStorage();
        String documentUrl = fileUrl.substring(0,fileUrl.lastIndexOf("/"));
        String encryptionDocumentName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        documentStorage.setDocumentName(fileName);
        documentStorage.setEncryptionDocumentName(encryptionDocumentName);
        documentStorage.setDocumentUrl(documentUrl+"/");
        documentStorageRepository.insertByObject(documentStorage);

    }



}
