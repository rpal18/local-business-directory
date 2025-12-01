package com.rohitPal.localBusinessDirectory.dto;

public class UserResponse {
    private Long userId;
    private String userName;

    private String address;
    private String email;
    private double longitude;
    private double latitude;

    public UserResponse() {
    }


    public UserResponse(Long userId, String userName, String address, double longitude, double latitude , String email) {
        this.userId = userId;
        this.userName = userName;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
