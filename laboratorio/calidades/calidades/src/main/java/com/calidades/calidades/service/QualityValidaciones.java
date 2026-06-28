package com.calidades.calidades.service;

import org.springframework.stereotype.Service;

import com.calidades.calidades.DTO.QualityDTO;
import com.calidades.calidades.model.Quality;

@Service
public class QualityValidaciones {
    public boolean validarNullVacio(Quality quality) {
        if (quality.getCantidadPh() == null || quality.getCantidadPh() < 3 || quality.getCantidadPh() > 5) {
            return false;
        }
        return true;
    }
    public QualityDTO convertirADTO(Quality q) {
        QualityDTO dto = new QualityDTO();
        dto.setIdQuality(q.getIdQuality());
        dto.setCantidadPh(q.getCantidadPh());
        dto.setControlQuality(q.isControlQuality());
        return dto;
    }

    public Quality convertirAEntidad(QualityDTO dto) {
        Quality q = new Quality();
        q.setIdQuality(dto.getIdQuality());
        q.setCantidadPh(dto.getCantidadPh());
        q.setControlQuality(dto.isControlQuality());
        return q;
    }


}
