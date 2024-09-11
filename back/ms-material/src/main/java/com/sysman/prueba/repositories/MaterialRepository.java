package com.sysman.prueba.repositories;

import com.sysman.prueba.entities.Ciudad;
import com.sysman.prueba.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    List<Material> getAll();
    List<Material> findByTipoAndFechaCompra(String tipo, LocalDate fechaCompra);

    List<Material> findAllByCiudad(Ciudad ciudad);

    Material save(Material material);

}
