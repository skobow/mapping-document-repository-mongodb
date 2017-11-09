package net.skobow.spring.data.mongodb.mapping;

public class MappingContext<TDocument, KDomain> {

    private final Class<TDocument> documentType;
    private final Class<KDomain> domainType;
    private final DocumentMapper<TDocument, KDomain> documentMapper;

    private String idFieldName = "id";

    public MappingContext(Class<TDocument> documentType, Class<KDomain> domainType, DocumentMapper<TDocument, KDomain> documentMapper) {
        this.documentType = documentType;
        this.domainType = domainType;
        this.documentMapper = documentMapper;
    }

    public MappingContext(Class<TDocument> documentType, Class<KDomain> domainType, DocumentMapper<TDocument, KDomain> documentMapper, String idFieldName) {
        this(documentType, domainType, documentMapper);
        this.idFieldName = idFieldName;
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

    public String getIdFieldName() {
        return idFieldName;
    }
}
