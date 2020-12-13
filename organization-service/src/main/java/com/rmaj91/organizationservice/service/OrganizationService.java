package com.rmaj91.organizationservice.service;

import com.rmaj91.organizationservice.domain.Organization;
import com.rmaj91.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Optional<Organization> findById(Long id) {
        return organizationRepository.findById(id);
    }

    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }
}
