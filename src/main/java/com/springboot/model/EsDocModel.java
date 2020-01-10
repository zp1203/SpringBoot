package com.springboot.model;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EsDocModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String documentName;
    private String encryptionDocumentName;
    private String documentUrl;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"documentName\":\"" + documentName + '\"' +
                ", \"encryptionDocumentName\":\"" + encryptionDocumentName + '\"' +
                ", \"documentUrl\":\"" + documentUrl + '\"' +
                '}';
    }

}
