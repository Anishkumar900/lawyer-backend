package com.lawyer.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Register {

    @Id

    private Long id;
    private String name;

    private String email;
    private String password;
    private String phoneNumber;
    private String profilePhoto;

    private Address address;

}
