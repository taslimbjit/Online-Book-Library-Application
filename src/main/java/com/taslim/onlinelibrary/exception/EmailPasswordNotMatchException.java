package com.taslim.onlinelibrary.exception;

public class EmailPasswordNotMatchException extends RuntimeException{
    public EmailPasswordNotMatchException(String message){
        super(message);
    }
}