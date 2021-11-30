package com.rst.organizationssrv.slices.organizations.db.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("organizations")
public class OrganizationEntity {
    @Id
    private long id;
    private String name;
}
