package com.envasados.envasados.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PackingDTO {
    
    private Integer idPacking;
    private String tipoEnvase;
    private Integer cantidadEnvases;

}
