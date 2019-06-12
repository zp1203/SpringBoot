package com.springboot.dao;

import com.springboot.model.DocumentStorageModel;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentStorageRepository {
    @Insert("insert into document_storage(documentName,encryptionDocumentName,documentUrl) values (#{documentName,jdbcType=VARCHAR},#{encryptionDocumentName,jdbcType=VARCHAR},#{documentUrl,jdbcType=VARCHAR})")
    int insertByObject(DocumentStorageModel documentStorageModel);
}
