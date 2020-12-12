package com.rmaj91.licensingservice.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class License {

    private String id;
    private String organizationId;
    private String productName;
    private String licenseType;
}
