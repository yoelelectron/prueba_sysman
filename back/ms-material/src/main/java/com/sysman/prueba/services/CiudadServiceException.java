package com.sysman.prueba.services;

public class CiudadServiceException extends RuntimeException {
    public CiudadServiceException(String message) {
        super(message);
    }

    public CiudadServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}