package com.sysman.prueba.services;

import com.sysman.prueba.entities.Ciudad;
import com.sysman.prueba.entities.Material;
import com.sysman.prueba.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
    }

    public List<Material> findAll(){
        return this.materialRepository.findAll();
    }

    public List<Material> findByTypeAndPurchaseDate(String type, LocalDate purchaseDate ){
        return this.materialRepository.findByTipoAndFechaCompra(type, purchaseDate);
    }

    public List<Material> findByCity(String ciudad){
        return this.materialRepository.findByCiudad(ciudad);
    }

    public Material create(Material material){
        return this.materialRepository.save((material));
    }

    public boolean exists(long id){
        return this.materialRepository.existsById(id);
    }

    public Material update(Material material){
        if(exists(material.getId())){
            return this.materialRepository.save((material));
        }

        return  null;
    }



}
