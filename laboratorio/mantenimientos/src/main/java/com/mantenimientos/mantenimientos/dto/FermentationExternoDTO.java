package com.mantenimientos.mantenimientos.dto;

import lombok.Data;

@Data
public class FermentationExternoDTO {
    private Integer idFermentation;
    private String codigoTanque;
    private Integer temperaturaActual;
}