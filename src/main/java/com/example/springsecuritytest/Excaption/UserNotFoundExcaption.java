package com.example.springsecuritytest.Excaption;

public class UserNotFoundExcaption extends Throwable{
    public UserNotFoundExcaption(String message) {
        super(message);
        System.out.println(message);
    }
}
