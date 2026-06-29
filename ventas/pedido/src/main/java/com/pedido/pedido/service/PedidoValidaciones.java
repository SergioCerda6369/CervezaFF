package com.pedido.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pedido.pedido.dto.DespachoExternoDTO;
import com.pedido.pedido.dto.PedidoDTO;
import com.pedido.pedido.dto.StockExternoDTO;
import com.pedido.pedido.model.Pedido;
import reactor.core.publisher.Mono;

@Service
public class PedidoValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public DespachoExternoDTO obtenerDespachoRemoto(Integer idPedido) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://despachos-service/api/despachos/pedido/" + idPedido)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(DespachoExternoDTO.class)
                    .block();
        } catch (Exception e) {
            return null;
        }
    }

    public StockExternoDTO obtenerStockRemoto(Integer idStockFinal) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://stocks-service/api/v1/stocks/" + idStockFinal)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(StockExternoDTO.class)
                    .block();
        } catch (Exception e) {
            StockExternoDTO fallback = new StockExternoDTO();
            fallback.setIdStockFinal(idStockFinal);
            fallback.setNombreCerveza("PRODUCTO NO DISPONIBLE");
            fallback.setPrecioUnitario(0.0);
            return fallback;
        }
    }

    public PedidoDTO convertirADTO(Pedido pedido) {
        return PedidoDTO.builder()
                .idPedido(pedido.getIdPedido())
                .cliente(pedido.getCliente())
                .idStockFinal(pedido.getIdStockFinal())
                .cantidadSolicitada(pedido.getCantidadSolicitada())
                .totalVenta(pedido.getTotalVenta())
                .estadoPedido(pedido.getEstadoPedido())
                .despacho(obtenerDespachoRemoto(pedido.getIdPedido()))
                .stock(obtenerStockRemoto(pedido.getIdStockFinal()))
                .build();
    }
}
