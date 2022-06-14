package com.gamotrance.OTT.Exception;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Could not find User "+id);
    }

    public UserNotFoundException(String name) {
        super("Could not find User "+name);
    }

}