package com.rmaj91.licensingservice.controller;

import com.rmaj91.licensingservice.domain.License;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @GetMapping("/{licenseId}")
    public License getLicense(@PathVariable("organizationId") String organizationId,
                              @PathVariable("licenseId")
                                      String licenseId) {
        License license = new License();
        license.setId(licenseId);
        license.setProductName("Teleco");
        license.setLicenseType("Seat");
        license.setOrganizationId(organizationId);
        return license;
    }
}
