package com.rohitPal.localBusinessDirectory.model;

import com.rohitPal.localBusinessDirectory.validation.ValidMxEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.locationtech.jts.geom.Point;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "name" , nullable = false)
    private String name;

    @NotBlank
    @Column(name = "mobile_number"  , nullable = false)
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[6-9]\\d{9}$" , message = "mobile number invalid!! Enter Valid Indian  mobile number :")
    private String mobileNumber;
    @NotBlank
    @Column(name ="address" , nullable = false)
    @Size(min = 5 , max = 50)
    private String address;
    @NotNull
    @Column(name="dob" , nullable = false , updatable = false)
    private LocalDate dateOfBirth;
    @Email
    @ValidMxEmail
    @Column(name = "email" , unique = true)
    private String email;
    @NotNull
    @Column(name = "location", columnDefinition = "geography(Point , 4326)")
    private Point location;

    public User() {
    }

    public User(Long id, String name, String mobileNumber, String address, LocalDate dateOfBirth, String email, Point location) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
