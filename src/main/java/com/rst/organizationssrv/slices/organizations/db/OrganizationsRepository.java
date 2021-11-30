package com.rst.organizationssrv.slices.organizations.db;

import com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationsRepository extends MongoRepository<OrganizationEntity, Long> {
}
