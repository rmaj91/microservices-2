package com.rmaj91.licensingservice.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class License {

    @Id
    @GeneratedValue
    private Long id;
    private String organizationId;
    private String productName;
    private String licenseId;
}
