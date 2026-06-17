package com.fermentaciones.fermentaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fermentaciones.fermentaciones.model.Fermentation;

@Repository
public interface FermentationRepository extends JpaRepository<Fermentation, Integer> {

}
