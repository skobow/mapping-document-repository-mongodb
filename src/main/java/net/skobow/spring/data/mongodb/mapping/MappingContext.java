/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.skobow.spring.data.mongodb.mapping;

import net.skobow.spring.data.mongodb.core.Document;

/**
 *
 * @author skobow
 */
public class MappingContext<TDocument extends Document, KDomain> {

    private final Class<TDocument> documentType;
    private final Class<KDomain> domainType;
    private final DocumentMapper<TDocument, KDomain> documentMapper;

    public MappingContext(Class<TDocument> documentType, Class<KDomain> domainType, DocumentMapper<TDocument, KDomain> documentMapper) {
        this.documentType = documentType;
        this.domainType = domainType;
        this.documentMapper = documentMapper;
    }
    
    public Class<TDocument> getDocumentType() {
        return documentType;
    }
    
    public Class<KDomain> getDomainType() {
        return domainType;
    }
    
    public DocumentMapper<TDocument, KDomain> getDocumentMapper() {
        return documentMapper;
    }
}
