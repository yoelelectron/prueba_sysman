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

@Service
@Slf4j
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<MaterialResponseDTO> findAll() {
        List<Material> materials = this.materialRepository.findAll();
        return materials.stream().map(material -> mapToMaterialResponse(material)).toList();
    }

    public List<Material> findByTypeAndPurchaseDate(String type, LocalDate purchaseDate) {
        return this.materialRepository.findByTipoAndFechaCompra(type, purchaseDate);
    }

    public List<Material> findByCity(String city) {
        return this.materialRepository.findByCiudad(city);
    }


    public void create(MaterialDTO materialRequest) {

        if(materialRequest.getFechaCompra().isAfter(materialRequest.getFechaVenta())){
            log.info("{} cannot be stored because purchase date is higher than selling date", materialRequest.getNombre());
            throw new Error("Dates are not good");
        } else {
            Material material = Material.builder()
                    .nombre(materialRequest.getNombre())
                    .descripcion(materialRequest.getDescripcion())
                    .tipo(materialRequest.getTipo())
                    .precio(materialRequest.getPrecio())
                    .fechaCompra(materialRequest.getFechaCompra())
                    .fechaVenta(materialRequest.getFechaVenta())
                    .estado(materialRequest.getEstado().name().toLowerCase())
                    .ciudad(materialRequest.getCiudad())
                    .build();

            this.materialRepository.save(material);
            log.info("Material {} is saved", material.getId());
        }


    }

    public boolean exists(long id) {
        return this.materialRepository.existsById(id);
    }

    public Material update(Material material) {
        if (exists(material.getId())) {
            log.info("Material {} has been updated", material.getId());
            return this.materialRepository.save((material));
        }
        return null;
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
