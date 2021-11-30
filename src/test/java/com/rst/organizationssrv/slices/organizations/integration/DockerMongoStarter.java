package com.rst.organizationssrv.slices.organizations.integration;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

// https://www.testcontainers.org/modules/databases/mongodb/
public class DockerMongoStarter {
    static final MongoDBContainer mongoDBContainer =
            new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
    static {
        mongoDBContainer.start();
    }

    // https://rieckpil.de/mongodb-testcontainers-setup-for-datamongotest/
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}