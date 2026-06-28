package com.pedido.pedido.dto;

import lombok.Data;

@Data
public class DespachoExternoDTO {
    private Integer idDispatch;       
    private String patenteCamion;     
    private String nombreConductor;   
    private String estadoDispatch;

}
