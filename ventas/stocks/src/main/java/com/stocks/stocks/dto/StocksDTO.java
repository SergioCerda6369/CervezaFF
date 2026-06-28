package com.stocks.stocks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class StocksDTO {
    private Integer idStockFinal;
    private String nombreCerveza;
    private Integer cantidadDisponible;
    private Double precioUnitario;
}
