# mapping-repository-spring-mongodb

[![Build Status](https://travis-ci.org/skobow/mapping-repository-spring-mongodb.svg?branch=develop)](https://travis-ci.org/skobow/mapping-repository-spring-mongodb)

This project provides a base implementation of a spring mongodb repository that automatically maps document to domain objects and vice versa.

# Synopsis

Following the principals of DDD, hexagonal design and/or onion architecture we usually do not want our domain classes to depend on anything else but the java framework itself. When it comes to creating applications using databases this usually ends up in massive creation of mapper code to keep the domain classes clean and not clutter them with JPA/Hibernate annotations. I recognized that I did the same thing over and over again so I decied to extract all that mapper logic to this little library.
It is based on mongodb and spring framework and can be used to facilitate the separation of domain classes and the corresponding mongodb document/entity classes.
