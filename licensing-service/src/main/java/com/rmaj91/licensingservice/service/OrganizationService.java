package com.rmaj91.licensingservice.service;

import com.rmaj91.licensingservice.controller.ClientType;
import com.rmaj91.licensingservice.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.rmaj91.licensingservice.controller.ClientType.*;
import static com.rmaj91.licensingservice.controller.ClientType.REST;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate injectedRestTemplate;
    private final OrganizationFeignClient organizationFeignClient;

    public Optional<Organization> findById(Long id, ClientType clientType) {
        Optional<Organization> result = Optional.empty();
        if (DISCOVERY.equals(clientType)) {
            RestTemplate restTemplate = new RestTemplate();
            List<ServiceInstance> instances = discoveryClient.getInstances("organization-service");
            ServiceInstance serviceInstance = instances.get(0);
            String url = serviceInstance.getUri().toString() + "/v1/organizations/" + id;
            result = Optional.ofNullable(restTemplate.getForObject(url, Organization.class, id));
        } else if (REST.equals(clientType)) {
            String url = "http://organization-service" + "/v1/organizations/" + id;
            result = Optional.ofNullable(injectedRestTemplate.getForObject(url, Organization.class, id));
        } else if (FEIGN.equals(clientType)) {
            result = Optional.ofNullable(organizationFeignClient.getOrganization(id));
        }
        return result;
    }
}
