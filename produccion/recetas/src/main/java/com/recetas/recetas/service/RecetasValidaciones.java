package com.recetas.recetas.service;

import org.springframework.stereotype.Service;
import com.recetas.recetas.dto.RecetasDTO;
import com.recetas.recetas.model.Recetas;

@Service
public class RecetasValidaciones {

    public RecetasDTO convertirADto(Recetas receta) {
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
