package com.sysman.prueba.exceptionHandler;

import com.sysman.prueba.services.CiudadServiceException;
import com.sysman.prueba.services.MaterialServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CiudadServiceException.class)
    public ResponseEntity<String> handleCiudadServiceException(CiudadServiceException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MaterialServiceException.class)
    public ResponseEntity<String> handleMaterialServiceException(MaterialServiceException e){
        if (e.getMessage().contains("No materials found")) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e){
        return new ResponseEntity<>("An unexpected error has occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
