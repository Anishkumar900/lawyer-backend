package com.lawyer.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Address {

    @Id
    private Long id;
    private String city;
    private String pinCode;
    private String state;
}
