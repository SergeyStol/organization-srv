package com.rst.organizationssrv.slices.organizations.service;

import com.rst.organizationssrv.slices.organizations.api.dto.OrganizationDto;
import com.rst.organizationssrv.slices.organizations.api.exceptions.NotFoundException;
import com.rst.organizationssrv.slices.organizations.db.OrganizationsRepository;
import com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.rst.organizationssrv.common.exceptions.ExceptionMessages.CANT_FIND_ORGANIZATION_WITH_ID;

@Service
@RequiredArgsConstructor
public class OrganizationsService {

    private final OrganizationsRepository repo;
    private final ModelMapper modelMapper;

    public OrganizationDto getOrganizationById(long id) {
        return repo.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new NotFoundException(CANT_FIND_ORGANIZATION_WITH_ID + id));
    }

    private OrganizationDto convertToDto(@NonNull OrganizationEntity organizationEntity) {
        return modelMapper.map(organizationEntity, OrganizationDto.class);
    }

    public List<OrganizationDto> getAllOrganizations() {
        return List.of();
    }

    public String addOrganization(OrganizationDto organizationDto) {
        return null;
    }

    public String updateOrganization(OrganizationDto organizationDto) {
        return null;
    }

    public void deleteOrganization(long id) {

    }
}