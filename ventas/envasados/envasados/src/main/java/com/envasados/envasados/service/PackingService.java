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

    @Autowired
    private PackingValidaciones validaciones;

    @Transactional
    public List<PackingDTO> obtenerTodos() {
        List<Packing> lista = packingRepository.findAll();
        List<PackingDTO> dtos = new ArrayList<>();
        for (Packing p : lista) {
            dtos.add(validaciones.convertirADTO(p));
        }
        return dtos;
    }

    @Transactional
    public PackingDTO buscarPorId(Integer id) {
        Packing p = packingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de envasado con ID " + id + " no encontrado."));
        return validaciones.convertirADTO(p);
    }

    @Transactional
    public PackingDTO guardar(Packing p) {
        return validaciones.convertirADTO(packingRepository.save(p));
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (packingRepository.existsById(id)) {
            packingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
