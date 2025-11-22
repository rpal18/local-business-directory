package com.rohitPal.localBusinessDirectory.exception;
/*
Custom exception class
 */

public class BusinessAlreadyExistsException extends RuntimeException{

    public BusinessAlreadyExistsException(String message){
        super(message);
    }
}
