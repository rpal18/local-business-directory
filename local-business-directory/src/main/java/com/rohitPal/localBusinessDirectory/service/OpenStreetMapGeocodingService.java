package com.rohitPal.localBusinessDirectory.service;
/*
At first RestClient object was tightly coupled , which was not good for production, that is
why to make it loosely coupled , I have created a Configuration class for injecting the object
whenever it is needed .

I also forgot to set timeout which is essential for production grade application.
------------------------------------------------------------------------------------------------
Old way (tightly coupled):
------------------------------------------------------------------------------------------------
     private GeometryFactory geometryFactory;
     private RestClient restClient;

     public OpenStreetMapGeocodingService() {
        this.geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        this.restClient = RestClient.builder()
                .baseUrl("https://nominatim.openstreetmap.org").build();
    }

===================================================================================================
new way is the current one implemented over here

 */

import com.rohitPal.localBusinessDirectory.dto.OsmResponse;
import org.locationtech.jts.geom.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.List;
import java.util.Optional;

@Service
public class OpenStreetMapGeocodingService implements GeocodingService {

    private  final GeometryFactory geometryFactory;
    private  final RestClient restClient;

    private static final Logger logger = LoggerFactory.getLogger(OpenStreetMapGeocodingService.class);


    @Autowired
    public OpenStreetMapGeocodingService(GeometryFactory geometryFactory , RestClient restClient ) {
        this.geometryFactory = geometryFactory;
        this.restClient = restClient;
    }

    @Override
    public Optional<Point> getCoordinates(String address) {
        try {
            logger.info("Requesting for coordinates for address , {}" ,address);
            List<OsmResponse> response = restClient.get().uri(UriBuilder -> UriBuilder.path("/search")
                            .queryParam("q", address)
                            .queryParam("format", "json")
                            .queryParam("limit", "1").build()).header("User-Agent",
                            "LocalBusinessDirectoryApplication")
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<OsmResponse>>() {
                    });

            if (response.isEmpty()) {
                logger.info("No coordinate found for address : {}" , address );
                return Optional.empty();
            }

            /*
            Till now response is in json form , we need to convert it from string to double .

             */

            OsmResponse bestMatch = response.get(0);

            double latitude = Double.parseDouble(bestMatch.latitude());
            double longitude = Double.parseDouble(bestMatch.longitude());

            /*
            Now for creating point
             */
            Point point = geometryFactory.createPoint(new Coordinate(longitude, latitude));

            return Optional.of(point);

        } catch (Exception e) {
            logger.error("Error geocoding address  : {}" , address , e);
            return Optional.empty();
        }

    }
}
