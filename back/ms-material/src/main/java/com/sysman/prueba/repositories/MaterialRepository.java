package com.sysman.prueba.repositories;

import com.sysman.prueba.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> findByTipoAndFechaCompra(String type, LocalDate purchaseDate);

    @Query(value = "SELECT m.* FROM t_material m JOIN t_ciudad c ON m.ciudad = c.codigo WHERE c.nombre = :ciudad", nativeQuery = true)
    List<Material> findByCiudad(String ciudad);
    Material save(Material material);
}
