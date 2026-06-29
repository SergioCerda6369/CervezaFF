package com.despachos.despachos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.despachos.despachos.DTO.DispatchDTO;
import com.despachos.despachos.model.Dispatch;
import com.despachos.despachos.repository.DispatchRepository;

import jakarta.transaction.Transactional;

@Service
public class DispatchService {

    @Autowired
    private DispatchRepository dispatchRepository;

    @Autowired
    private DispatchValidaciones validaciones;

    @Transactional
    public List<DispatchDTO> obtenerTodos() {
        List<Dispatch> lista = dispatchRepository.findAll();
        List<DispatchDTO> dtos = new ArrayList<>();
        for (Dispatch d : lista) {
            dtos.add(validaciones.convertirADTO(d));
        }
        return dtos;
    }

    @Transactional
    public DispatchDTO buscarPorId(Integer id) {
        Dispatch d = dispatchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despacho con ID " + id + " no encontrado."));
        return validaciones.convertirADTO(d);
    }

    @Transactional
    public DispatchDTO guardar(Dispatch d) {
        return validaciones.convertirADTO(dispatchRepository.save(d));
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (dispatchRepository.existsById(id)) {
            dispatchRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public DispatchDTO buscarPorIdPedido(Integer idPedido) {
        return dispatchRepository.findByIdPedido(idPedido)
                .map(validaciones::convertirADTO)
                .orElse(null);
    }
}
