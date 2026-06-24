package com.pedido.pedido.dto;

import lombok.Data;

@Data
public class StockExternoDTO {

    private Integer idStockFinal; 
    private String nombreCerveza;
    private Integer cantidadDisponible;
    private Double precioUnitario;

}
