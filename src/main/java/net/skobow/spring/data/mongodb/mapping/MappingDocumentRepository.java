package net.skobow.spring.data.mongodb.mapping;

import java.util.Optional;
import net.skobow.spring.data.mongodb.core.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MappingDocumentRepository<TDocument extends Document, KDomain> {

    private final MongoTemplate mongoTemplate;
    private final MappingContext<TDocument, KDomain> mappingContext;

    @Autowired
    public MappingDocumentRepository(
            MongoTemplate mongoTemplate,
            MappingContext<TDocument, KDomain> mappingContext) {
        this.mongoTemplate = mongoTemplate;
        this.mappingContext = mappingContext;
    }

    public Optional<KDomain> findById(final String id) {   
        return findOneByFieldValue("id", id);
    }
    
    protected Optional<KDomain> findOneByFieldValue(final String fieldName, final String fieldValue) {
        final Query query = getQuery(fieldName, fieldValue);
        final TDocument document = querySingleDocument(query);

        return mapDocumentToDomainObject(document);
    }
    
    private Query getQuery(final String field, final Object value) {
        return getQuery(Criteria.where(field).is(value));
    }
    
    private Query getQuery(final Criteria criteria) {
        return Query.query(criteria);
    }
    
    private TDocument querySingleDocument(final Query query) {
        return mongoTemplate.findOne(query, mappingContext.getDocumentType());
    }
    
    protected Optional<KDomain> mapDocumentToDomainObject(final TDocument document) {
        if (document == null) {
            return Optional.empty();
        }
        final KDomain domainObject = mappingContext.getDocumentMapper().map(document);
        return Optional.ofNullable(domainObject);
    }
}
