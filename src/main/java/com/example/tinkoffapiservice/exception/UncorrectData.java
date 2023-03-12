package com.example.tinkoffapiservice.exception;


public class UncorrectData extends RuntimeException{
    public UncorrectData(String message) {
        super(message);
    }
}
