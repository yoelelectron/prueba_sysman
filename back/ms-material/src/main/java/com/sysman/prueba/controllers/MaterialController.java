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
    @ResponseStatus(HttpStatus.OK)
    public List<MaterialResponseDTO> getAll() {
        return this.materialService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody MaterialDTO materialRequest){
        this.materialService.create(materialRequest);
    }

    @PutMapping
    public ResponseEntity<Material> update(@RequestBody Material material){
        if(this.materialService.update(material) != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/porTipoYfecha")
    public ResponseEntity<List<Material>> getByKindAndPurchaseDate(@RequestParam String type,
                                                                   @RequestParam LocalDate purchaseDate){
        return ResponseEntity.ok(this.materialService.findByTypeAndPurchaseDate(type, purchaseDate));
    }

    @GetMapping("/porCiudad/{city}")
    public ResponseEntity<List<Material>> getByCity(@PathVariable("city") String city){
        return ResponseEntity.ok(this.materialService.findByCity(city));
    }

/*    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Material>> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.materialService.getById(id));
    }*/
}
