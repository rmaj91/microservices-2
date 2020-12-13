package com.rmaj91.organizationservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Organization {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
}
