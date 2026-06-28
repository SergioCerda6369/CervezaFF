package com.producciones.producciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.producciones.producciones.dto.ProduccionesDTO;
import com.producciones.producciones.model.Producciones;
import com.producciones.producciones.repository.ProduccionesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProduccionesService {

    @Autowired
    private ProduccionesRepository produccionesRepository;

    @Autowired
    private ProduccionesValidaciones validaciones; 

    public List<ProduccionesDTO> obtenerTodos() {
        List<ProduccionesDTO> listaDTOs = new ArrayList<>();
        for (Producciones p : produccionesRepository.findAll()) {
            listaDTOs.add(validaciones.convertirADTO(p));
        }
        return listaDTOs;
    }

    public ProduccionesDTO buscarPorId(Integer id) {
        Producciones produccion = produccionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La orden de producción con ID " + id + " no existe."));
        return validaciones.convertirADTO(produccion);
    }

    public ProduccionesDTO guardar(Producciones produccion) {
        Producciones produccionGuardada = produccionesRepository.save(produccion);
        return validaciones.convertirADTO(produccionGuardada);
    }
}