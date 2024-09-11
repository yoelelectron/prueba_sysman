package com.sysman.prueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departamento_id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 60)
    private String codigo;

    @Column(nullable = false, length = 150)
    private String nombre;
}
