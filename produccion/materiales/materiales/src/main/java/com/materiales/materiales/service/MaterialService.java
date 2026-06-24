package com.materiales.materiales.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiales.materiales.DTO.MaterialDTO;
import com.materiales.materiales.model.Material;
import com.materiales.materiales.repository.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
public class MaterialService {
    @Autowired
    private MaterialRepository materialRepository;

    @Transactional
    public List<MaterialDTO> obtenerTodos() {
        List<Material> lista = materialRepository.findAll();
        List<MaterialDTO> dtos = new ArrayList<>();
        for (Material m : lista) {
            dtos.add(convertirADTO(m));
        }
        return dtos;
    }

    @Transactional
    public MaterialDTO guardar(Material m) {
        return convertirADTO(materialRepository.save(m));
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private MaterialDTO convertirADTO(Material m) {
        MaterialDTO dto = new MaterialDTO();
        dto.setIdMaterial(m.getIdMaterial());
        dto.setNombreMaterial(m.getNombreMaterial());
        dto.setCantidadStock(m.getCantidadStock());
        dto.setNombreProveedor(m.getNombreProveedor());
        return dto;
    }

}
