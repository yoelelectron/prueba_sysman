package com.sysman.prueba.services;

import com.sysman.prueba.DTO.MaterialDTO;
import com.sysman.prueba.DTO.MaterialResponseDTO;
import com.sysman.prueba.entities.Material;
import com.sysman.prueba.repositories.MaterialRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<MaterialResponseDTO> findAll() {
        try{
            List<Material> materials = this.materialRepository.findAll();
            return materials.stream().map(material -> mapToMaterialResponse(material)).toList();
        } catch (Exception ex){
            throw new MaterialServiceException("Error fetching materials", ex);
        }
    }

    public List<Material> findByTypeAndPurchaseDate(String type, LocalDate purchaseDate) {
        if (type == null || type.trim().isEmpty()) {
            throw new MaterialServiceException("Type parameter cannot be null or empty");
        }
        if (purchaseDate == null) {
            throw new MaterialServiceException("Purchase date cannot be null");
        }

        try {
            List<Material> materials = this.materialRepository.findByTipoAndFechaCompra(type, purchaseDate);
            if (materials.isEmpty()) {
                throw new MaterialServiceException("No materials found for type: " + type + " and/or purchase date: " + purchaseDate);
            }
            return materials;
        } catch (Exception e) {
            throw new MaterialServiceException("Error fetching materials by type and purchase date", e);
        }
    }

    public List<Material> findByCity(String city) {
        if(city == null || city.trim().isEmpty()){
            throw new MaterialServiceException("City parameter cannot be null or empty");
        }

        try {
            List<Material> materials = this.materialRepository.findByCiudad(city);
            if(materials.isEmpty()){
                throw new MaterialServiceException("No materials found for city" + city);
            }
            return materials;

        } catch (Exception e){
            throw  new MaterialServiceException("Error fetching materials by city: " + city, e);
        }
    }


    public void create(MaterialDTO materialRequest) {

        if(materialRequest.getFechaCompra().isAfter(materialRequest.getFechaVenta())){
            log.info("{} cannot be stored because purchase date is higher than selling date", materialRequest.getNombre());
            throw new MaterialServiceException("Purchase date cannot be later than selling date for material: " + materialRequest.getNombre());
        } else {
            try{
                Material material = Material.builder()
                        .nombre(materialRequest.getNombre())
                        .descripcion(materialRequest.getDescripcion())
                        .tipo(materialRequest.getTipo())
                        .precio(materialRequest.getPrecio())
                        .fechaCompra(materialRequest.getFechaCompra())
                        .fechaVenta(materialRequest.getFechaVenta())
                        .estado(materialRequest.getEstado().name().toUpperCase())
                        .ciudad(materialRequest.getCiudad())
                        .build();

                this.materialRepository.save(material);
                log.info("Material {} is saved", material.getId());
            } catch (Exception ex){
                throw new MaterialServiceException("Error saving material: " + materialRequest.getNombre(), ex);
            }
        }
    }

    public boolean exists(long id) {
        return this.materialRepository.existsById(id);
    }

    public Material update(Material material) {
        if (!exists(material.getId())) {
            throw new MaterialServiceException("Material not found with ID: " + material.getId());
        }
        try {
            log.info("Material {} has been updated", material.getId());
            return this.materialRepository.save((material));
        } catch (Exception ex){
            throw new MaterialServiceException("Error updating material with ID: " + material.getId(), ex);
        }
    }

    private MaterialResponseDTO mapToMaterialResponse(Material material) {
        return MaterialResponseDTO.builder()
                .id(material.getId())
                .nombre(material.getNombre())
                .descripcion(material.getDescripcion())
                .tipo(material.getTipo())
                .precio(material.getPrecio())
                .fechaCompra(material.getFechaCompra())
                .fechaVenta(material.getFechaVenta())
                .estado(material.getEstado())
                .ciudad(material.getCiudad())
                .build();
    }
}
