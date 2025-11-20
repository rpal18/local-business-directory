package com.rohitPal.local._business_directory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "businessName" , nullable = false)
    @Size(min = 3 , max = 50)
    private String businessName;
    @NotBlank
    @Column(name = "address" , nullable = false)
    private String address;
    @NotBlank
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[6-9]\\d{9}$" , message = "mobile number invalid!! Enter Valid Indian  mobile number :")
    private String contactNumber;
    @Email
    @Column(name = "email" , nullable = false)
    private String email;
}
