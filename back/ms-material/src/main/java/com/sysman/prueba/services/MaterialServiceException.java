package com.sysman.prueba.services;

public class MaterialServiceException extends RuntimeException{

    public MaterialServiceException(String message){
        super(message);
    }

    public MaterialServiceException(String message, Throwable cause){
        super(message,cause);
    }
}
