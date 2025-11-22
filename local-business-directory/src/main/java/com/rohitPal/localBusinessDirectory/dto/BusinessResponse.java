package com.rohitPal.localBusinessDirectory.dto;

import com.rohitPal.localBusinessDirectory.model.Category;
import org.locationtech.jts.geom.Point;

public class BusinessResponse {

    private Long businessId;
    private String businessName;
    private String contactNumber;

    private String email;
    private String website;
    private String address;
    private Category category;

    private double longitude;
    private double latitude;
    private double distanceInKm;

    public BusinessResponse() {
    }

    public BusinessResponse(String businessName, String contactNumber, String email, String website, String address, Category category, double longitude , double latitude ,Long businessId , double distanceInKm) {
        this.businessName = businessName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.website = website;
        this.address = address;
        this.category = category;
        this.latitude = latitude;
        this.businessId = businessId;
        this.longitude = longitude;
        this.distanceInKm = distanceInKm;

    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public double getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(double distanceInKm) {
        this.distanceInKm = distanceInKm;
    }
}
