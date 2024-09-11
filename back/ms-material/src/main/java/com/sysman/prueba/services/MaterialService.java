package com.sysman.prueba.services;

import com.sysman.prueba.entities.Material;
import com.sysman.prueba.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
    }

    public List<Material> findAll(){
        return this.materialRepository.getAll();
    }

}
