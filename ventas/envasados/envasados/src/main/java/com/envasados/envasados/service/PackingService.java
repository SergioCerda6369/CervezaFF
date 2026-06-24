package com.envasados.envasados.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.envasados.envasados.DTO.PackingDTO;
import com.envasados.envasados.model.Packing;
import com.envasados.envasados.repository.PackingRepository;

import jakarta.transaction.Transactional;

@Service
public class PackingService {

    @Autowired
    private PackingRepository packingRepository;

    @Transactional
    public List<PackingDTO> obtenerTodos() {
        List<Packing> lista = packingRepository.findAll();
        List<PackingDTO> dtos = new ArrayList<>();
        for (Packing p : lista) {
            dtos.add(convertirADTO(p));
        }
        return dtos;
    }

    @Transactional
    public PackingDTO guardar(Packing p) {
        return convertirADTO(packingRepository.save(p));
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (packingRepository.existsById(id)) {
            packingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PackingDTO convertirADTO(Packing p) {
        PackingDTO dto = new PackingDTO();
        dto.setIdPacking(p.getIdPacking());
        dto.setTipoEnvase(p.getTipoEnvase());
        dto.setCantidadEnvases(p.getCantidadEnvases());
        return dto;
    }

}
