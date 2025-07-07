package com.lawyer.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.lawyer.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phoneNumber;
    private String profilePhoto;
    @Enumerated(EnumType.STRING)
    private USER_ROLE role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id" , referencedColumnName = "id")
    private Address address;

}
