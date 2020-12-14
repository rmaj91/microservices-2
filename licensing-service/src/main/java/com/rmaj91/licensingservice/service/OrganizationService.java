package com.rmaj91.licensingservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rmaj91.licensingservice.controller.ClientType;
import com.rmaj91.licensingservice.domain.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.rmaj91.licensingservice.controller.ClientType.*;
import static com.rmaj91.licensingservice.controller.ClientType.REST;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate injectedRestTemplate;
    private final OrganizationFeignClient organizationFeignClient;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name = "coreSize",value="30"), // max threadpool size
            @HystrixProperty(name="maxQueueSize", value="10") ,// max queue size
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value ="10"),//
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value ="75"),//
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value ="15000"),//
            @HystrixProperty(name="metrics.rollingStats.numBuckets", value ="5")//
    },
            fallbackMethod = "fallBackMethod",
            threadPoolKey = "customThreadPool")
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

    // method must have same signature, be in same class and can be private
    private Optional<Organization> fallBackMethod(Long id, ClientType clientType) {
        Organization organization = new Organization();
        organization.setDescription("Not available!");
        return Optional.of(organization);
    }
}
