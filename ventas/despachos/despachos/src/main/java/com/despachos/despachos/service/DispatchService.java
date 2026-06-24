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

    @Transactional
    public List<DispatchDTO> obtenerTodos() {
        List<Dispatch> lista = dispatchRepository.findAll();
        List<DispatchDTO> dtos = new ArrayList<>();
        for (Dispatch d : lista) {
            dtos.add(convertirADTO(d));
        }
        return dtos;
    }

    @Transactional
    public DispatchDTO guardar(Dispatch d) {
        return convertirADTO(dispatchRepository.save(d));
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (dispatchRepository.existsById(id)) {
            dispatchRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DispatchDTO convertirADTO(Dispatch d) {
        DispatchDTO dto = new DispatchDTO();
        dto.setIdDispatch(d.getIdDispatch());
        dto.setPatenteCamion(d.getPatenteCamion());
        dto.setNombreConductor(d.getNombreConductor());
        dto.setEstadoDispatch(d.getEstadoDispatch());
        return dto;
    }

    @Transactional
    public DispatchDTO buscarPorIdPedido (Integer idPedido) {
        return dispatchRepository.findByIdPedido(idPedido)
            .map(this::convertirADTO)
            .orElse(null);
    }

}
