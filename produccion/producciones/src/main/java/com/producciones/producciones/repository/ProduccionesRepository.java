package com.producciones.producciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.producciones.producciones.model.Producciones;

@Repository
public interface ProduccionesRepository extends JpaRepository<Producciones, Integer> {
}
