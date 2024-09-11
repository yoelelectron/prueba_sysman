package com.sysman.prueba.controllers;

import com.sysman.prueba.DTO.CiudadResponseDTO;
import com.sysman.prueba.services.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    @Autowired
    public CiudadController(CiudadService ciudadService){
        this.ciudadService = ciudadService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CiudadResponseDTO> getAll() {
        return this.ciudadService.findAll();
    }
}
