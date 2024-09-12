package com.sysman.prueba.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
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
    @Column(nullable = false, columnDefinition = "Decimal(10,2)")
    private Double precio;
    @Column(nullable = false, columnDefinition = "DATETIME", name = "fecha_compra")
    private LocalDate fechaCompra;
    @Column(nullable = false, columnDefinition = "DATETIME", name = "fecha_venta")
    private LocalDate fechaVenta;
    @Column(nullable = false)
    private String estado;
    @ManyToOne()
    @JoinColumn(name = "ciudad")
    private Ciudad ciudad;

}
