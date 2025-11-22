package com.rohitPal.localBusinessDirectory.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/*
Here , I am making use of record it is used as a carrier to tranfer to one place to another .
in record all the fields are final , hence we cannot change it.

It is on primitive fields .

it is different from class in many ways .
it automatically generates Getters , Constructor but do not generate Setter method .

(in short we should use record when we do not need to do any further modification )

*/

@JsonIgnoreProperties(ignoreUnknown = true)
public record OsmResponse(
        @JsonProperty("lat")
        String latitude ,
        @JsonProperty("lon")
        String longitude
) {}
