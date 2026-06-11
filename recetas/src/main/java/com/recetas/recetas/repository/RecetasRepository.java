package com.recetas.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.recetas.recetas.model.Recetas;

@Repository
public interface RecetasRepository extends JpaRepository<Recetas, Integer> {
}
