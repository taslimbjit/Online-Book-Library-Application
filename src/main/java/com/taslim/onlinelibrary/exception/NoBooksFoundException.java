package com.taslim.onlinelibrary.exception;

public class NoBooksFoundException extends RuntimeException{
    public NoBooksFoundException(String message){
        super(message);
    }
}