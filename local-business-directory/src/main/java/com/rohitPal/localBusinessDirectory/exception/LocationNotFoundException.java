package com.rohitPal.localBusinessDirectory.exception;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException(String message){
        super(message);
    }
}
