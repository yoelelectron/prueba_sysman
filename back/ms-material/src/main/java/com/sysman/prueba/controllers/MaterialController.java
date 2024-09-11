package com.sysman.prueba.controllers;

import com.sysman.prueba.entities.Material;
import com.sysman.prueba.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<List<Material>> getAll(){
        return ResponseEntity.ok(this.materialService.findAll());
    }

    @PostMapping
    public ResponseEntity<Material> save(@RequestBody Material material){
        return new ResponseEntity<>(this.materialService.create(material), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Material> update(@RequestBody Material material){
        if(this.materialService.update(material) != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/porTipoYfecha")
    public ResponseEntity<List<Material>> getByKindAndPurchaseDate(@RequestParam String kind, @RequestParam LocalDate date){
        return ResponseEntity.ok(this.materialService.findByTypeAndPurchaseDate(kind, date));
    }

    @GetMapping("/porCiudad/{city}")
    public ResponseEntity<List<Material>> getByCity(@PathVariable("city") String city){
        return ResponseEntity.ok(this.materialService.findByCity(city));
    }
}
