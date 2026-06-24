package com.calidades.calidades.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.calidades.calidades.model.Quality;

@Repository
public interface QualityRepository extends JpaRepository<Quality, Integer>{

}
