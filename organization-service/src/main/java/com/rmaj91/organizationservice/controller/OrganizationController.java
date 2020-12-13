package com.rmaj91.organizationservice.controller;

import com.rmaj91.organizationservice.domain.Organization;
import com.rmaj91.organizationservice.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/organizations/{id}")
    public ResponseEntity<Organization> getOrganization(@PathVariable Long id) {
        return organizationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/organizations")
    public ResponseEntity<Organization> create(@RequestBody Organization organization) {
        return ResponseEntity.status(CREATED).body(organizationService.create(organization));
    }
}
