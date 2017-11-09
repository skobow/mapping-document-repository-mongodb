package net.skobow.spring.data.mongodb.mapping;

public interface DocumentMapper<TDocument, KDomain> {
    KDomain map(TDocument from);
    TDocument mapReverse(KDomain from);
}
