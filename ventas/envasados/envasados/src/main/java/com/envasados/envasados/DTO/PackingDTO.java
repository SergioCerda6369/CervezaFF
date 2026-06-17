package com.envasados.envasados.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackingDTO {
    
    private Integer idPacking;
    private String tipoEnvase;
    private Integer cantidadEnvases;

}
