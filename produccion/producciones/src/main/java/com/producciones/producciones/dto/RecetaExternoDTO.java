package com.producciones.producciones.dto;

import lombok.Data;

@Data
public class RecetaExternoDTO {

    private Integer idReceta;
    private String nombreCerveza;
    private String descripcion;
    private String ingredientes;
    private Integer tiempoCoccionMinutos;
    private Integer diasFermentacion;

}
