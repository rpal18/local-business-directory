package com.rohitPal.localBusinessDirectory.exception;

public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists(String message){
        super(message);
    }
}
