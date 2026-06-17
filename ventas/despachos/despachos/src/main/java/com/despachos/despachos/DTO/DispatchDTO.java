package com.despachos.despachos.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispatchDTO {
    private Integer idDispatch;
    private String patenteCamion;
    private String nombreConductor;
    private String estadoDispatch;
}
