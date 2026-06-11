package com.producciones.producciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.producciones.producciones.dto.ProduccionesDTO;
import com.producciones.producciones.model.Producciones;
import com.producciones.producciones.repository.ProduccionesRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduccionesService {

    @Autowired
    private ProduccionesRepository produccionesRepository;

    public List<ProduccionesDTO> obtenerTodos() {
        return produccionesRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public ProduccionesDTO buscarPorId(Integer id) {
        Producciones produccion = produccionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La orden de producción con ID " + id + " no existe."));
        return convertirADto(produccion);
    }

    public ProduccionesDTO guardar(Producciones produccion) {
        Producciones produccionGuardada = produccionesRepository.save(produccion);
        return convertirADto(produccionGuardada);
    }

    private ProduccionesDTO convertirADto(Producciones produccion) {
    return ProduccionesDTO.builder()
            .idProduccion(produccion.getIdProduccion())
            .idReceta(produccion.getIdReceta())
            .codigoLote(produccion.getCodigoLote())
            .cantidadLitros(produccion.getCantidadLitros())
            .fechaInicio(produccion.getFechaInicio())
            .estadoProduccion(produccion.getEstadoProduccion())
            .build();
}
}