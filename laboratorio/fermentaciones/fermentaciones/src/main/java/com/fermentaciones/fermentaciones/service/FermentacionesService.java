package com.fermentaciones.fermentaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.model.Fermentation;
import com.fermentaciones.fermentaciones.repository.FermentationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FermentacionesService {

    @Autowired
    private FermentationRepository fermentationRepository;

    @Autowired
    private FermentationValidaciones validaciones;

    public List<FermentationDTO> obtenerTodos() {
        List<FermentationDTO> listaDTOs = new ArrayList<>();
        for (Fermentation f : fermentationRepository.findAll()) {
            listaDTOs.add(validaciones.convertirADTO(f));
        }
        return listaDTOs;
    }

    public FermentationDTO buscarPorId(Integer id) {
        Fermentation f = fermentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de fermentación no encontrado."));
        return validaciones.convertirADTO(f);
    }

    public boolean eliminar(Integer id) {
        if (fermentationRepository.existsById(id)) {
            fermentationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public FermentationDTO guardar(Fermentation fermentation) {
        Fermentation guardada = fermentationRepository.save(fermentation);
        return validaciones.convertirADTO(guardada);
    }
}
