package com.mantenimientos.mantenimientos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mantenimientos.mantenimientos.dto.MantenimientosDTO;
import com.mantenimientos.mantenimientos.model.Mantenimientos;
import com.mantenimientos.mantenimientos.repository.MantenimientosRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MantenimientosService {

    @Autowired
    private MantenimientosRepository mantenimientosRepository;

    @Autowired
    private MantenimientosValidaciones validaciones;

    public List<MantenimientosDTO> obtenerTodos() {
        return mantenimientosRepository.findAll().stream()
                .map(validaciones::convertirADto)
                .collect(Collectors.toList());
    }

    public MantenimientosDTO buscarPorId(Integer id) {
        Mantenimientos mantenimiento = mantenimientosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El registro de mantenimiento con ID " + id + " no existe."));
        return validaciones.convertirADto(mantenimiento);
    }

    public MantenimientosDTO guardar(Mantenimientos mantenimiento) {
        Mantenimientos guardado = mantenimientosRepository.save(mantenimiento);
        return validaciones.convertirADto(guardado);
    }
}
