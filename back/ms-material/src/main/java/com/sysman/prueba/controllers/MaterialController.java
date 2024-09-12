package com.sysman.prueba.controllers;

import com.sysman.prueba.DTO.MaterialDTO;
import com.sysman.prueba.DTO.MaterialResponseDTO;
import com.sysman.prueba.entities.Material;
import com.sysman.prueba.services.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todas los materiales",
            description = "Devuelve una lista de todas las materiales disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de materiales encontrados exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron materiales"),
    })
    @GetMapping
    public ResponseEntity<List<MaterialResponseDTO>> getAll() {
        List<MaterialResponseDTO> materials = this.materialService.findAll();
        if(materials.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @Operation(summary = "Crear un nuevo material", description = "Guarda un nuevo material en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
    })
    @PostMapping
    public ResponseEntity<String> save(@RequestBody MaterialDTO materialRequest){
        this.materialService.create(materialRequest);
        return  new ResponseEntity<>("Material created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Crear un nuevo material", description = "Guarda un nuevo material en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Material creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta", content = @Content),
    })
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Material material){
        this.materialService.update(material);
        return new ResponseEntity<>("Material updated successfully", HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas los materiales filtrados por tipo y fecha de compra",
            description = "Devuelve una lista de todas las materiales filtrados por tipo y fecha de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de materiales encontrados exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontraron materiales"),
    })
    @GetMapping("/porTipoYfecha")
    public ResponseEntity<List<Material>> getByKindAndPurchaseDate(@Parameter(description = "tipo de material")
                                                                    @RequestParam String type,
                                                                   @Parameter(description = "Fecha de la compra en formato yyyy-mm-dd",
                                                                           example = "2023-02-01")
                                                                   @RequestParam LocalDate purchaseDate){
        List<Material> materials = this.materialService.findByTypeAndPurchaseDate(type, purchaseDate);
        if(materials.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @Operation(summary = "Obtener todas los materiales filtrados por ciudad",
            description = "Devuelve una lista de todas las materiales filtrados por tipo y fecha de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de materiales por ciudad encontrados"),
            @ApiResponse(responseCode = "204", description = "No se encontraron materiales"),
    })
    @GetMapping("/porCiudad/{city}")
    public ResponseEntity<List<Material>> getByCity(@Parameter(description = "Ciudad por la que se desea filtrar",
                                                        example = "New York")
                                                        @PathVariable("city") String city){
        List<Material> materials = this.materialService.findByCity(city);
        if(materials.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

}
