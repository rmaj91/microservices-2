package com.rmaj91.licensingservice.service;

import com.rmaj91.licensingservice.domain.License;
import com.rmaj91.licensingservice.repository.LicenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;

    public Optional<License> find(String organizationId, String licenseId) {
        return licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
    }

    public License create(License license) {
        return licenseRepository.save(license);
    }
}
