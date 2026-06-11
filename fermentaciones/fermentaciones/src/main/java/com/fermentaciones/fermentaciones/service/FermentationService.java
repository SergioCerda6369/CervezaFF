package com.fermentaciones.fermentaciones.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.model.Fermentation;
import com.fermentaciones.fermentaciones.repository.FermentationRepository;

import jakarta.transaction.Transactional;

@Service
public class FermentationService {

    @Autowired
    private FermentationRepository fermentationRepository;

    public List<FermentationDTO> obtenerTodos() {
        List<Fermentation> lista = fermentationRepository.findAll();
        List<FermentationDTO> dtos = new ArrayList<>();
        for (Fermentation f : lista) {
            dtos.add(convertirADTO(f));
        }
        return dtos;
    }

    @Transactional
    public FermentationDTO guardar(Fermentation f) {
        return convertirADTO(fermentationRepository.save(f));
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (fermentationRepository.existsById(id)) {
            fermentationRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    private FermentationDTO convertirADTO(Fermentation f) {
        FermentationDTO dto = new FermentationDTO();
        dto.setIdFermentation(f.getIdFermentation());
        dto.setCodigoTanque(f.getCodigoTanque());
        dto.setTemperaturaActual(f.getTemperaturaActual());
        return dto;
    }

}
