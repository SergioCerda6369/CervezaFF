package com.materiales.materiales.DTO;

import lombok.Data;

@Data
public class MaterialDTO {
    
    private Integer idMaterial;
    private String nombreMaterial;
    private Integer cantidadStock;
    private String nombreProveedor;

}
