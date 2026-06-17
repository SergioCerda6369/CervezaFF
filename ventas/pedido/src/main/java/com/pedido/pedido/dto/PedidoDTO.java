package com.pedido.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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