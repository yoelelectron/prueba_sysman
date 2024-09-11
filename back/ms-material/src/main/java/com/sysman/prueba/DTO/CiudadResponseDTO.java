package com.sysman.prueba.DTO;

import com.sysman.prueba.entities.Departamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CiudadResponseDTO {

    private Long codigo;

    private String nombre;

    private Departamento departamento;
}
