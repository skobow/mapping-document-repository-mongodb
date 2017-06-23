package net.skobow.spring.data.mongodb;

import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class AbstractDocumentRepository {
    private final MongoTemplate mongoTemplate;

    protected AbstractDocumentRepository(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
