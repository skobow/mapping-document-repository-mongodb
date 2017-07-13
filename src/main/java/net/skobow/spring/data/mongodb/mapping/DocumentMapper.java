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
public interface DocumentMapper<TDocument extends Document, KDomain> {

    KDomain map(TDocument from);

    TDocument mapReverse(KDomain from);

    void map(TDocument from, KDomain to);

    void mapReverse(KDomain from, TDocument to);
}
