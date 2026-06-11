package com.pedido.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pedido.pedido.dto.PedidoDTO;
import com.pedido.pedido.repository.PedidoRepository;

import jakarta.transaction.Transactional;

import com.pedido.pedido.model.Pedido;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

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
        return PedidoDTO.builder()
            .idPedido(pedido.getIdPedido())
            .cliente(pedido.getCliente())
            .idStockFinal(pedido.getIdStockFinal())
            .cantidadSolicitada(pedido.getCantidadSolicitada())
            .totalVenta(pedido.getTotalVenta())
            .estadoPedido(pedido.getEstadoPedido())
            .build();
    }
}