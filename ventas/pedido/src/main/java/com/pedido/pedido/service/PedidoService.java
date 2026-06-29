package com.pedido.pedido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import com.pedido.pedido.dto.PedidoDTO;
import com.pedido.pedido.model.Pedido;
import com.pedido.pedido.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoValidaciones validaciones;

    public List<PedidoDTO> obtenerTodos() {
        List<PedidoDTO> listaDTOs = new ArrayList<>();
        for (Pedido p : pedidoRepository.findAll()) {
            listaDTOs.add(validaciones.convertirADTO(p));
        }
        return listaDTOs;
    }

    public PedidoDTO buscarPorId(Integer id) {
        Pedido p = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado en los registros de la fábrica"));
        return validaciones.convertirADTO(p);
    }

    public PedidoDTO guardar(Pedido nuevoPedido) {
        Pedido guardado = pedidoRepository.save(nuevoPedido);
        return validaciones.convertirADTO(guardado);
    }
}