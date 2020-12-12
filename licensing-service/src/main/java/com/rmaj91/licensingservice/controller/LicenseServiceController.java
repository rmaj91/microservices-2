package com.rmaj91.licensingservice.controller;

import com.rmaj91.licensingservice.domain.License;
import com.rmaj91.licensingservice.service.LicenseService;
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

    @GetMapping("/organizations/{organizationId}/licenses/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") String organizationId,
                                              @PathVariable("licenseId") String licenseId) {
        return licenseService.find(organizationId, licenseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/licenses")
    public ResponseEntity<License> create(@RequestBody License license) {
        return ResponseEntity.status(CREATED).body(licenseService.create(license));
    }
}
