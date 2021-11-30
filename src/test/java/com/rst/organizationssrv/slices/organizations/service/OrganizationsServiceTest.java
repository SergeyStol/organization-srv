package com.rst.organizationssrv.slices.organizations.service;

import com.rst.organizationssrv.common.exceptions.ExceptionMessages;
import com.rst.organizationssrv.slices.organizations.api.dto.OrganizationDto;
import com.rst.organizationssrv.slices.organizations.api.exceptions.NotFoundException;
import com.rst.organizationssrv.slices.organizations.db.OrganizationsRepository;
import com.rst.organizationssrv.slices.organizations.db.entities.OrganizationEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrganizationsServiceTest {

    @Mock
    OrganizationsRepository mockRepository;
    @Spy
    ModelMapper modelMapper = new ModelMapper();
    @InjectMocks
    OrganizationsService organizationsService;

    final long ORGANIZATION_ID = 1;
    final String ORGANIZATION_NAME = "Organization1";
    final OrganizationEntity organizationEntity =
            new OrganizationEntity(ORGANIZATION_ID, ORGANIZATION_NAME);
    final OrganizationDto organizationDto =
            new OrganizationDto(ORGANIZATION_ID, ORGANIZATION_NAME);

    // ****************************************************
    // public Object getOrganizationById(@NonNull long id)
    // ****************************************************
    @Test
    void shouldGetSubscriptionById() {
        when(mockRepository.findById(ORGANIZATION_ID))
                .thenReturn(Optional.of(organizationEntity));
        assertThat(organizationsService.getOrganizationById(ORGANIZATION_ID))
                .isEqualTo(organizationDto);
        verify(mockRepository).findById(ORGANIZATION_ID);
    }

    @Test
    void shouldThrowExceptionWhenSubscriptionIsNotFound() {
        when(mockRepository.findById(any())).thenReturn(Optional.empty());
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> organizationsService.getOrganizationById(ORGANIZATION_ID))
                .withMessage(ExceptionMessages.CANT_FIND_ORGANIZATION_WITH_ID + ORGANIZATION_ID);
    }

    // *******************************************************************
    // OrganizationDto convertToDto(OrganizationEntity organizationEntity)
    // *******************************************************************
    @Test
    void shouldThrowNPEWhenOrganizationEntityNull() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> ReflectionTestUtils.
                        invokeMethod(organizationsService,
                                "convertToDto", (OrganizationEntity)null));
    }

    @Test
    void shouldReturnDto() {
        OrganizationDto organizationDtoActual = ReflectionTestUtils
                .invokeMethod(organizationsService, "convertToDto", organizationEntity);
        assertThat(organizationDtoActual).isEqualTo(organizationDto);
    }
}