package com.mantenimientos.mantenimientos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mantenimientos.mantenimientos.model.Mantenimientos;

@Repository
public interface MantenimientosRepository extends JpaRepository<Mantenimientos, Integer> {
}
