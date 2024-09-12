package com.sysman.prueba.DTO;

import com.sysman.prueba.entities.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private Double precio;
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;
    private String estado;
    private Ciudad ciudad;
}
