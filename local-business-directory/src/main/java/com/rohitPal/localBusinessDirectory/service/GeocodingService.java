package com.rohitPal.localBusinessDirectory.service;

import org.locationtech.jts.geom.Point;

import java.util.Optional;

public interface GeocodingService {
    Optional<Point> getCoordinates(String address);
}
