package com.materiales.materiales.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.materiales.materiales.model.Material;

@Repository
public interface MaterialRepository  extends JpaRepository<Material, Integer> {
    List<Material> findByIdMaterial(Integer id_material);

}
