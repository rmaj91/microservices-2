package com.rmaj91.licensingservice.service;

import com.rmaj91.licensingservice.domain.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("organization-service")
public interface OrganizationFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/organizations/{id}")
    Organization getOrganization(@PathVariable Long id);
}
