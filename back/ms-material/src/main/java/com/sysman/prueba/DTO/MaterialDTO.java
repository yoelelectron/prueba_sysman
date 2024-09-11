package com.sysman.prueba.DTO;

import com.sysman.prueba.entities.Ciudad;

import com.sysman.prueba.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDTO {

    private String nombre;

    private String descripcion;

    private String tipo;

    private Double precio;

    private LocalDate fechaCompra;

    private LocalDate fechaVenta;

    private Estado estado;

    private Ciudad ciudad;
}
