package com.pedido.pedido.dto;

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
public class PedidoDTO {
    private Integer idPedido;
    private String cliente;
    private Integer idStockFinal;
    private Integer cantidadSolicitada;
    private Double totalVenta;
    private String estadoPedido;

    private DespachoExternoDTO despacho;
    private StockExternoDTO stock;
}