package com.rst.organizationssrv.slices.organizations.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rst.organizationssrv.slices.organizations.api.dto.OrganizationDto;
import com.rst.organizationssrv.slices.organizations.service.OrganizationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.rst.organizationssrv.common.api.ApiConstants.API_VERSION;
import static com.rst.organizationssrv.common.api.ApiConstants.ORGANIZATIONS_API_PREFIX;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class OrganizationsControllerTest {
    final int ORGANIZATION_ID = 1;
    final String ORGANIZATION_NAME = "Organization1";
    final OrganizationDto organizationDto =
            new OrganizationDto(ORGANIZATION_ID, ORGANIZATION_NAME);

    @Mock
    private OrganizationsService mockService;

    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(new OrganizationsController(mockService)).build();
    }

    // ********************************************************************************
    // public Object getOrganizationByName(@PathVariable("organization_name") String organizationName)
    // ********************************************************************************
    @Test
    void shouldSuccessfullyGetOrganizationById() throws Exception {
        when(mockService.getOrganizationById(ORGANIZATION_ID)).thenReturn(organizationDto);
        mockMvc.perform(get(API_VERSION + ORGANIZATIONS_API_PREFIX + "/" + ORGANIZATION_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(organizationDto)))
                .andExpect(jsonPath("$.id", is(ORGANIZATION_ID)))
                .andExpect(jsonPath("$.name", is(ORGANIZATION_NAME)))
                .andExpect(jsonPath("$.*", hasSize(2)));
    }
}