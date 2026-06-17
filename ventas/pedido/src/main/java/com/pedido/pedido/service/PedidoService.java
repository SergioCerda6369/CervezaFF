package com.pedido.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.pedido.pedido.dto.DespachoExternoDTO;
import com.pedido.pedido.dto.PedidoDTO;
import com.pedido.pedido.dto.StockExternoDTO;
import com.pedido.pedido.repository.PedidoRepository;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;

import com.pedido.pedido.model.Pedido;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class PedidoService {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    private PedidoRepository pedidoRepository;

    PedidoService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public List<PedidoDTO> obtenerTodos() {
        List<PedidoDTO> listaDTOs = new ArrayList<>();
        List<Pedido> pedidosReales = pedidoRepository.findAll();
        for (Pedido p : pedidosReales) {
            listaDTOs.add(convertirADTO(p));
        }
        return listaDTOs;
    }

    public PedidoDTO buscarPorId(Integer id) {
        Pedido p = pedidoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado en los registros de la fábrica"));
        return convertirADTO(p);
    }

    public PedidoDTO guardar(Pedido nuevoPedido) {
        Pedido guardado = pedidoRepository.save(nuevoPedido);
        return convertirADTO(guardado);
    }

    private PedidoDTO convertirADTO(Pedido pedido) {
        PedidoDTO dto = PedidoDTO.builder()
            .idPedido(pedido.getIdPedido())
            .cliente(pedido.getCliente())
            .idStockFinal(pedido.getIdStockFinal())
            .cantidadSolicitada(pedido.getCantidadSolicitada())
            .totalVenta(pedido.getTotalVenta())
            .estadoPedido(pedido.getEstadoPedido())
            .build();

        try {
            DespachoExternoDTO despachoRecuperado = webClientBuilder.build()
                .get()
                .uri("http://127.0.0.1:8082/api/despachos/pedido/" + pedido.getIdPedido())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(DespachoExternoDTO.class)
                .block();

            dto.setDespacho(despachoRecuperado);
            
        } catch (Exception e) {
            dto.setDespacho(null); 
        }
        try {
            StockExternoDTO stockRecuperado = webClientBuilder.build()
                .get()
                .uri("http://127.0.0.1:8090/api/v1/stocks/" + pedido.getIdStockFinal())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(StockExternoDTO.class)
                .block();

            dto.setStock(stockRecuperado);
            
        } catch (Exception e) {
            dto.setStock(null);
        }

        return dto;
    }
    
}