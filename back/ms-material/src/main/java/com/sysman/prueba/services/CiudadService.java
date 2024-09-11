package com.sysman.prueba.services;

import com.sysman.prueba.DTO.CiudadResponseDTO;
import com.sysman.prueba.entities.Ciudad;
import com.sysman.prueba.repositories.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    @Autowired
    public CiudadService(CiudadRepository ciudadRepository){
        this.ciudadRepository = ciudadRepository;
    }

    public List<CiudadResponseDTO> findAll() {
        List<Ciudad> ciudades = this.ciudadRepository.findAll();
        return ciudades.stream().map(ciudad -> mapToCiudadResponse(ciudad)).toList();
    }

    private CiudadResponseDTO mapToCiudadResponse(Ciudad ciudad) {
        return CiudadResponseDTO.builder()
                .codigo(ciudad.getCodigo())
                .nombre(ciudad.getNombre())
                .departamento(ciudad.getDepartamento())
                .build();
    }
}
