package com.rmaj91.licensingservice.controller;

import com.rmaj91.licensingservice.domain.License;
import com.rmaj91.licensingservice.domain.Organization;
import com.rmaj91.licensingservice.service.LicenseService;
import com.rmaj91.licensingservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class LicenseServiceController {

    private final LicenseService licenseService;
    private final OrganizationService organizationService;

    @GetMapping("/licenses/{id}")
    public ResponseEntity<License> getLicense(@PathVariable("id") Long id) {
        return licenseService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/organizations/{id}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("id") Long id, @RequestParam() ClientType clientType) {
        return organizationService.findById(id, clientType)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/licenses")
    public ResponseEntity<License> create(@RequestBody License license) {
        return ResponseEntity.status(CREATED).body(licenseService.create(license));
    }
}
