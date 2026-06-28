package com.fermentaciones.fermentaciones.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FermentationDTO {
    private Integer idFermentation;
    private String codigoTanque;
    private Integer temperaturaActual;

    private QualityExternoDTO quality;

}
