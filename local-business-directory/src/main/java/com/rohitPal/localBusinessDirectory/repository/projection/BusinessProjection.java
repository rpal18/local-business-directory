package com.rohitPal.localBusinessDirectory.repository.projection;

import com.rohitPal.localBusinessDirectory.model.Category;

public interface BusinessProjection {
    // 1. Business Data
    Long getBusinessId();
    String getBusinessName();
    String getContactNumber();
    String getEmail();
    String getWebsite();
    String getAddress();
    Category getCategory();

    // We need lat/lon specifically to map to your DTO
    Double getLatitude();
    Double getLongitude();

    // 2. The Calculated Distance
    Double getDistance();
}