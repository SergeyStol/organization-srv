package com.rst.organizationssrv;

import com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class OrganizationsSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganizationsSrvApplication.class, args);
    }

}