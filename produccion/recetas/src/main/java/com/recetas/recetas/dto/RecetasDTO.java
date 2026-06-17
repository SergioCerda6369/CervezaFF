package com.recetas.recetas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecetasDTO {
    private Integer idReceta;
    private String nombreCerveza;
    private String descripcion;
    private String ingredientes;
    private Integer tiempoCoccionMinutos;
    private Integer diasFermentacion;

}
