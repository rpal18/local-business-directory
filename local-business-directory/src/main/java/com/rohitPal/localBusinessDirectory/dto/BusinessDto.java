package com.rohitPal.localBusinessDirectory.dto;

import com.rohitPal.localBusinessDirectory.model.Category;
import com.rohitPal.localBusinessDirectory.validation.ValidMxEmail;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
/*
in this BusinessDto class I have made use of @URL to validate whether the entered website is valid
or not , it comes form hibernate validators .
the main thing about this annotation is it allows null entry and get triggered when something has
entered.
 */


public class BusinessDto {
    private Long businessId;
    @NotBlank(message = "Business name is required !")
    @Size(min = 5 , max = 50 , message = "Length of Business name should be between 5 and 50 character")
    private String name;
    @URL
    private String website;
    @NotBlank(message = "Address is required ")
    private String address;
    @NotBlank(message = "phone number can not be null")
    @Pattern(regexp = "^(\\+91[\\-\\s]?)?[6-9]\\d{9}$" , message = "mobile number invalid!! Enter Valid Indian  mobile number :")
    private String contactNumber;
    @NotNull(message = "Category is required !!")
    private Category category;
    @Email
    @ValidMxEmail
    @NotBlank(message = "Email is required to register")
    private String email;

    public BusinessDto() {
    }

    public BusinessDto(Long businessId, String name, String website, String address, String contactNumber, Category category, String email) {
        this.businessId = businessId;
        this.name = name;
        this.website = website;
        this.address = address;
        this.contactNumber = contactNumber;
        this.category = category;
        this.email = email;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
