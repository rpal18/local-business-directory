package com.rohitPal.localBusinessDirectory.model;

import com.rohitPal.localBusinessDirectory.validation.ValidMxEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;


@Entity

@Table(name = "businesses", indexes = {
        @Index(name = "idx_business_location", columnList = "location")
})
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "business_name", nullable = false)
    @Size(min = 3, max = 50)
    private String businessName;
    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;
    @NotBlank
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[6-9]\\d{9}$", message = "mobile number invalid!! Enter Valid Indian  mobile number :")
    @Column(name = "contact_number")
    private String contactNumber;
    @Email
    @Column(name = "email", unique = true)
    @NotBlank
    @ValidMxEmail
    private String email;
    @NotNull
    @Column(name = "location", columnDefinition = "geography(Point , 4326)")
    private Point location;
    @Column(name = "website")
    private String businessWebsite;
    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private Category businessCategory;
    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Business() {
    }

    public Business(Long id, String businessName, String address, String contactNumber, String email, Point location, String businessWebsite, Category businessCategory, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.businessName = businessName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
        this.location = location;
        this.businessWebsite = businessWebsite;
        this.businessCategory = businessCategory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public String getBusinessWebsite() {
        return businessWebsite;
    }

    public void setBusinessWebsite(String businessWebsite) {
        this.businessWebsite = businessWebsite;
    }

    public Category getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(Category businessCategory) {
        this.businessCategory = businessCategory;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
