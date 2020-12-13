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

    public License create(License license) {
        return licenseRepository.save(license);
    }

    public Optional<License> findById(Long id) {
        return licenseRepository.findById(id);
    }
}
