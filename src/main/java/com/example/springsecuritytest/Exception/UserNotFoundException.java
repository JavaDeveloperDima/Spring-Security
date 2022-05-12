package com.example.springsecuritytest.Exception;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
