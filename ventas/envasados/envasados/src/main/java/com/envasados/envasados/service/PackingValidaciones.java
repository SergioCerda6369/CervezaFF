package com.envasados.envasados.service;

import org.springframework.stereotype.Service;
import com.envasados.envasados.DTO.PackingDTO;
import com.envasados.envasados.model.Packing;

@Service
public class PackingValidaciones {

    public PackingDTO convertirADTO(Packing p) {
        PackingDTO dto = new PackingDTO();
        dto.setIdPacking(p.getIdPacking());
        dto.setTipoEnvase(p.getTipoEnvase());
        dto.setCantidadEnvases(p.getCantidadEnvases());
        return dto;
    }
}