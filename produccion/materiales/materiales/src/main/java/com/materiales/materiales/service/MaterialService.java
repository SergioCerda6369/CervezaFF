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

    @Autowired
    private MaterialValidaciones validaciones;

    @Transactional
    public List<MaterialDTO> obtenerTodos() {
        List<Material> lista = materialRepository.findAll();
        List<MaterialDTO> dtos = new ArrayList<>();
        for (Material m : lista) {
            dtos.add(validaciones.convertirADTO(m));
        }
        return dtos;
    }

    @Transactional
    public MaterialDTO buscarPorId(Integer id) {
        Material m = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material con ID " + id + " no encontrado."));
        return validaciones.convertirADTO(m);
    }

    @Transactional
    public MaterialDTO guardar(Material m) {
        validaciones.validarCampos(m);
        return validaciones.convertirADTO(materialRepository.save(m));
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
            return true;
        }
        return false;
    }
}