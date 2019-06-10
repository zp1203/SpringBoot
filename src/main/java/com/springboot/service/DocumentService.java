package com.springboot.service;

import com.springboot.dao.DocumentStorageRepository;
import com.springboot.model.DocumentStorageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentStorageRepository documentStorageRepository;

    public void saveDocument(String fileName,String fileUrl){
        DocumentStorageModel documentStorageModel = new DocumentStorageModel();
        String documentUrl = fileUrl.substring(0,fileUrl.lastIndexOf("/"));
        String encryptionDocumentName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        documentStorageModel.setDocumentName(fileName);
        documentStorageModel.setEncryptionDocumentName(encryptionDocumentName);
        documentStorageModel.setDocumentUrl(documentUrl+"/");
        documentStorageRepository.insertByObject(documentStorageModel);

    }



}
