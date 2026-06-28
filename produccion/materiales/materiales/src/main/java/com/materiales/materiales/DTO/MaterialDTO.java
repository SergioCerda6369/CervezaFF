package com.materiales.materiales.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MaterialDTO {
    
    private Integer idMaterial;
    private String nombreMaterial;
    private Integer cantidadStock;
    private String nombreProveedor;

}
