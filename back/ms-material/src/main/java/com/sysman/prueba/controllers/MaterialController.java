package com.sysman.prueba.controllers;

import com.sysman.prueba.DTO.MaterialDTO;
import com.sysman.prueba.DTO.MaterialResponseDTO;
import com.sysman.prueba.entities.Material;
import com.sysman.prueba.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> getAll() {
        List<MaterialResponseDTO> materials = this.materialService.findAll();
        if(materials.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody MaterialDTO materialRequest){
        this.materialService.create(materialRequest);
        return  new ResponseEntity<>("Material created successfully", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody Material material){
        this.materialService.update(material);
        return new ResponseEntity<>("Material updated successfully", HttpStatus.OK);
    }

    @GetMapping("/porTipoYfecha")
    public ResponseEntity<List<Material>> getByKindAndPurchaseDate(@RequestParam String type,
                                                                   @RequestParam LocalDate purchaseDate){
        List<Material> materials = this.materialService.findByTypeAndPurchaseDate(type, purchaseDate);
        if(materials.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("/porCiudad/{city}")
    public ResponseEntity<List<Material>> getByCity(@PathVariable("city") String city){
        List<Material> materials = this.materialService.findByCity(city);
        if(materials.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

}
