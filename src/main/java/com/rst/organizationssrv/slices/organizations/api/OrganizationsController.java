package com.rst.organizationssrv.slices.organizations.api;

import com.rst.organizationssrv.slices.organizations.api.dto.OrganizationDto;
import com.rst.organizationssrv.slices.organizations.service.OrganizationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.rst.organizationssrv.common.api.ApiConstants.API_VERSION;
import static com.rst.organizationssrv.common.api.ApiConstants.ORGANIZATIONS_API_PREFIX;

@RestController
@RequestMapping(API_VERSION + ORGANIZATIONS_API_PREFIX)
@RequiredArgsConstructor
public class OrganizationsController {
    private final OrganizationsService service;

    // GET /v1/organizations/:id
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrganizationDto getOrganizationById(@PathVariable long id) {
        return service.getOrganizationById(id);
    }

    // POST /v1/organizations
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addOrganization(@RequestBody OrganizationDto organizationDto) {
        return service.addOrganization(organizationDto);
    }

    // PUT /v1/organizations
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateOrganization(OrganizationDto organizationDto) {
        service.updateOrganization(organizationDto);
    }

    // DELETE /v1/organizations/:id
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization(@PathVariable long id) {
        service.deleteOrganization(id);
    }
}