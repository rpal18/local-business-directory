package com.rohitPal.localBusinessDirectory.repository.projection;

import com.rohitPal.localBusinessDirectory.model.Business;

public interface BusinessProjection {
    Business getBusiness();

    double getDistance();
}
