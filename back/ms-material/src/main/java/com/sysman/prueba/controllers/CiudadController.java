package com.sysman.prueba.controllers;

import com.sysman.prueba.DTO.CiudadResponseDTO;
import com.sysman.prueba.services.CiudadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "Obtener todas las ciudades", description = "Devuelve una lista de todas las ciudades disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ciudades encontrada exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron ciudades"),
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<CiudadResponseDTO>> getAll(){
        List<CiudadResponseDTO> ciudades = this.ciudadService.findAll();

        if(ciudades.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ciudades,HttpStatus.OK);
    }
}
