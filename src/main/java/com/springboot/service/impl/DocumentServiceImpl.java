package com.springboot.service.impl;

import com.springboot.dao.DocumentStorageRepository;
import com.springboot.model.DocumentStorageModel;
import com.springboot.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentStorageRepository documentStorageRepository;

    @Override
    public void saveDocument(String fileName, String fileUrl) {
        DocumentStorageModel documentStorageModel = new DocumentStorageModel();
        String documentUrl = fileUrl.substring(0,fileUrl.lastIndexOf("/"));
        String encryptionDocumentName = fileUrl.substring(fileUrl.lastIndexOf("/")+1);
        documentStorageModel.setDocumentName(fileName);
        documentStorageModel.setEncryptionDocumentName(encryptionDocumentName);
        documentStorageModel.setDocumentUrl(documentUrl+"/");
        documentStorageRepository.insertByObject(documentStorageModel);

    }



}
