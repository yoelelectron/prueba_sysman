package com.sysman.prueba.services;

import com.sysman.prueba.DTO.MaterialDTO;
import com.sysman.prueba.DTO.MaterialResponseDTO;
import com.sysman.prueba.entities.Ciudad;
import com.sysman.prueba.entities.Departamento;
import com.sysman.prueba.entities.Material;
import com.sysman.prueba.enums.Estado;
import com.sysman.prueba.repositories.MaterialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialService materialService;

    private Material material;
    private MaterialDTO materialDTO;

    @BeforeEach
    void setUp(){
        Departamento depto = new Departamento(1L, "Bogota");
        Ciudad ciudad = new Ciudad(1L,"Bogota",depto);
        material = Material.builder()
                .id(1L)
                .nombre("Material 1")
                .descripcion("Descripcion de prueba")
                .tipo("Tipo 1")
                .precio(10.6)
                .fechaCompra(LocalDate.of(2023,1,1))
                .fechaVenta(LocalDate.of(2023,12,31))
                .estado("ACTIVO")
                .ciudad(ciudad)
                .build();
        materialDTO = MaterialDTO.builder()
                .nombre("Material 1")
                .descripcion("Descripción de prueba")
                .tipo("Tipo 1")
                .precio(10.6)
                .fechaCompra(LocalDate.of(2023, 1, 1))
                .fechaVenta(LocalDate.of(2023, 12, 31))
                .estado(Estado.ACTIVO)
                .ciudad(ciudad)
                .build();
    }

    @Test
    void findAll_shouldReturnListOfMaterials() {
        List<Material> materials = List.of(material);
        when(materialRepository.findAll()).thenReturn(materials);

        List<MaterialResponseDTO> result = materialService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(materialRepository, times(1)).findAll();
    }

    @Test
    void findAll_shouldThrowExceptionWhenErrorOccurs() {
        when(materialRepository.findAll()).thenThrow(new RuntimeException("DB error"));

        MaterialServiceException exception = assertThrows(MaterialServiceException.class, () -> materialService.findAll());
        assertEquals("Error fetching materials", exception.getMessage());
        verify(materialRepository, times(1)).findAll();
    }

    @Test
    void findByTypeAndPurchaseDate_shouldReturnMaterials() {
        List<Material> materials = List.of(material);
        when(materialRepository.findByTipoAndFechaCompra("Tipo A", LocalDate.of(2023, 1, 1))).thenReturn(materials);

        List<Material> result = materialService.findByTypeAndPurchaseDate("Tipo A", LocalDate.of(2023, 1, 1));

        assertNotNull(result);
        assertEquals(1, ((List<?>) result).size());
        verify(materialRepository, times(1)).findByTipoAndFechaCompra("Tipo A", LocalDate.of(2023, 1, 1));
    }

    @Test
    void findByTypeAndPurchaseDate_shouldThrowExceptionWhenNoMaterialsFound() {

        when(materialRepository.findByTipoAndFechaCompra("Tipo A", LocalDate.of(2023, 1, 1))).thenReturn(new ArrayList<>());

        MaterialServiceException exception = assertThrows(MaterialServiceException.class, () ->
                materialService.findByTypeAndPurchaseDate("Tipo A", LocalDate.of(2023, 1, 1)));
        assertEquals("Error fetching materials by type and purchase date", exception.getMessage());
    }

    @Test
    void create_shouldSaveMaterial() {
        materialService.create(materialDTO);
        verify(materialRepository, times(1)).save(any(Material.class));
    }

    @Test
    void create_shouldThrowExceptionForInvalidDates() {
        materialDTO.setFechaCompra(LocalDate.of(2024, 1, 1));
        materialDTO.setFechaVenta(LocalDate.of(2023, 12, 31));

        MaterialServiceException exception = assertThrows(MaterialServiceException.class, () -> materialService.create(materialDTO));
        assertEquals("Purchase date cannot be later than selling date for material: Material 1", exception.getMessage());
    }

    @Test
    void update_shouldUpdateMaterialIfExists() {

        when(materialRepository.existsById(1L)).thenReturn(true);
        when(materialRepository.save(material)).thenReturn(material);

        Material result = materialService.update(material);

        assertNotNull(result);
        verify(materialRepository, times(1)).save(material);
    }

    @Test
    void update_shouldThrowExceptionIfMaterialDoesNotExist() {

        when(materialRepository.existsById(1L)).thenReturn(false);

        MaterialServiceException exception = assertThrows(MaterialServiceException.class, () -> materialService.update(material));
        assertEquals("Material not found with ID: 1", exception.getMessage());
    }
}
