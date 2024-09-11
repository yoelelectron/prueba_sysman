package com.sysman.prueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String descripcion;

    @Column(nullable = false, length = 150)
    private String tipo;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double precio;

    @Column(nullable = false, columnDefinition = "DATETIME", name = "fecha_compra")
    private LocalDate fechaCompra;

    @Column(nullable = false, columnDefinition = "DATETIME", name = "fecha_venta")
    private LocalDate fechaVenta;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ciudad")
    private Ciudad ciudad;

}
