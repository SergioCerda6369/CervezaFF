package com.materiales.materiales.service;

import org.springframework.stereotype.Service;
import com.materiales.materiales.DTO.MaterialDTO;
import com.materiales.materiales.model.Material;

@Service
public class MaterialValidaciones {

    public MaterialDTO convertirADTO(Material m) {
        MaterialDTO dto = new MaterialDTO();
        dto.setIdMaterial(m.getIdMaterial());
        dto.setNombreMaterial(m.getNombreMaterial());
        dto.setCantidadStock(m.getCantidadStock());
        dto.setNombreProveedor(m.getNombreProveedor());
        return dto;
    }

    public void validarCampos(Material m) {
        if (m.getCantidadStock() < 0) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa.");
        }
    }
}