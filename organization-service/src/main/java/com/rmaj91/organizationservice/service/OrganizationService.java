package com.rmaj91.organizationservice.service;

import com.rmaj91.organizationservice.domain.Organization;
import com.rmaj91.organizationservice.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Optional<Organization> findById(Long id) {
        //for hystrix example
        simulateSleep();
        return organizationRepository.findById(id);
    }

    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }

    private void simulateSleep() {
        Random random = new Random();
        int i = random.nextInt(3) + 1;
        if (i == 3) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
