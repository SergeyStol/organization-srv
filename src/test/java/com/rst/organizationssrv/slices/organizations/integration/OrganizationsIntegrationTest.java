package com.rst.organizationssrv.slices.organizations.integration;

import com.rst.organizationssrv.slices.organizations.api.OrganizationsController;
import com.rst.organizationssrv.slices.organizations.api.dto.OrganizationDto;
import com.rst.organizationssrv.slices.organizations.db.OrganizationsRepository;
import com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity;
import com.rst.organizationssrv.slices.organizations.service.OrganizationsService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static com.rst.organizationssrv.common.api.ApiConstants.API_VERSION;
import static com.rst.organizationssrv.common.api.ApiConstants.ORGANIZATIONS_API_PREFIX;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ContextConfiguration(classes = {
        OrganizationsRepository.class,
        OrganizationsService.class,
        OrganizationsController.class,
        ModelMapper.class})
@AutoConfigureDataMongo
@EnableMongoRepositories(basePackageClasses = OrganizationsRepository.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableWebMvc // https://stackoverflow.com/questions/14124110/spring-mvc-testframework-fails-with-http-response-406
    @PropertySource()
@Profile()
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrganizationsIntegrationTest extends DockerMongoStarter {

    final ResultMatcher _200_OK = MockMvcResultMatchers.status().isOk();
    final ResultMatcher _404_NOT_FOUND = MockMvcResultMatchers.status().isNotFound();

    final long ORGANIZATION_ID = 1;
    final String ORGANIZATION_NAME = "Organization1";
    final OrganizationDto organizationDto = new OrganizationDto(ORGANIZATION_ID, ORGANIZATION_NAME);
    final OrganizationEntity organizationEntity = new OrganizationEntity(ORGANIZATION_ID, ORGANIZATION_NAME);

    @Autowired
    OrganizationsRepository repo;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MongoTemplate template;

    @Autowired
    ApplicationContext ctx;

    ObjectMapper mapper = new ObjectMapper();

    @AfterAll
    void afterAll() {
        template.dropCollection(OrganizationEntity.class);
    }

    @BeforeEach
    void beforeEach() {
        template.dropCollection(OrganizationEntity.class);
        repo.insert(organizationEntity);
    }

    @Test
    void shouldReturn200OK() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(API_VERSION + ORGANIZATIONS_API_PREFIX + "/" + ORGANIZATION_ID);
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(_200_OK)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(organizationDto)));
    }
}