package com.recetas.recetas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recetas.recetas.dto.RecetasDTO;
import com.recetas.recetas.model.Recetas;
import com.recetas.recetas.repository.RecetasRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecetasService {

    @Autowired
    private RecetasRepository recetasRepository;

    public List<RecetasDTO> obtenerTodos() {
        return recetasRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public RecetasDTO buscarPorId(Integer id) {
        Recetas receta = recetasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La receta con ID " + id + " no existe."));
        return convertirADto(receta);
    }

    public RecetasDTO guardar(Recetas receta) {
        Recetas recetaGuardada = recetasRepository.save(receta);
        return convertirADto(recetaGuardada);
    }

    private RecetasDTO convertirADto(Recetas receta) {
        return RecetasDTO.builder()
                .idReceta(receta.getIdReceta())
                .nombreCerveza(receta.getNombreCerveza())
                .descripcion(receta.getDescripcion())
                .ingredientes(receta.getIngredientes())
                .tiempoCoccionMinutos(receta.getTiempoCoccionMinutos())
                .diasFermentacion(receta.getDiasFermentacion())
                .build();
    }
}