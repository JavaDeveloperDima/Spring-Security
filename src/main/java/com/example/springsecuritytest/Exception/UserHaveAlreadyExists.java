package com.example.springsecuritytest.Exception;

public class UserHaveAlreadyExists extends Throwable{
    public UserHaveAlreadyExists(String message) {
        super(message);
    }
}
