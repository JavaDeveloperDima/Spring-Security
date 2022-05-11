package com.example.springsecuritytest.Excaption;

public class UserHaveAlreadyExists extends Throwable{
    public UserHaveAlreadyExists(String message) {
        super(message);
    }
}
