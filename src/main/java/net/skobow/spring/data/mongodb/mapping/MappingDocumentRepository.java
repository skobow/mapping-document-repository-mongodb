package net.skobow.spring.data.mongodb.mapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mongodb.WriteResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public abstract class MappingDocumentRepository<TDocument, KDomain> {

    private final MongoTemplate mongoTemplate;
    private final MappingContext<TDocument, KDomain> mappingContext;

    public MappingDocumentRepository(
            final MongoTemplate mongoTemplate,
            final MappingContext<TDocument, KDomain> mappingContext) {
        this.mongoTemplate = mongoTemplate;
        this.mappingContext = mappingContext;
    }

    public Optional<KDomain> findById(final String id) {   
        return findOneByFieldValue(mappingContext.getIdFieldName(), id);
    }

    private Optional<KDomain> findOneByFieldValue(final String fieldName, final Object fieldValue) {
        final Query query = getQuery(fieldName, fieldValue);

        return querySingleDocument(query)
                .map(this::mapDocumentToDomainObject);
    }

    public List<KDomain> findAll() {
        return mongoTemplate.findAll(mappingContext.getDocumentType()).parallelStream()
                .map(this::mapDocumentToDomainObject)
                .collect(Collectors.toList());
    }

    public KDomain save(final KDomain domainObject) {
        TDocument document = mapDomainObjectToDocument(domainObject);
        mongoTemplate.save(document);

        return mapDocumentToDomainObject(document);
    }

    public void save(final List<KDomain> domainObjects) {
        List<TDocument> documents = domainObjects.parallelStream()
                .map(this::mapDomainObjectToDocument)
                .collect(Collectors.toList());

        mongoTemplate.insertAll(documents);
    }

    public boolean delete(final KDomain domainObject) {
        TDocument document = mapDomainObjectToDocument(domainObject);
        WriteResult result = mongoTemplate.remove(document);

        return result.getN() > 0;
    }

    private Query getQuery(final String field, final Object value) {
        return getQuery(Criteria.where(field).is(value));
    }
    
    private Query getQuery(final Criteria criteria) {
        return Query.query(criteria);
    }
    
    private Optional<TDocument> querySingleDocument(final Query query) {
        return Optional.ofNullable(mongoTemplate.findOne(query, mappingContext.getDocumentType()));
    }
    
    private KDomain mapDocumentToDomainObject(final TDocument document) {
        return mappingContext.getDocumentMapper().map(document);
    }

    private TDocument mapDomainObjectToDocument(final KDomain domainObject) {
        return mappingContext.getDocumentMapper().mapReverse(domainObject);
    }
}
