package com.calidades.calidades.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calidades.calidades.DTO.QualityDTO;
import com.calidades.calidades.model.Quality;
import com.calidades.calidades.repository.QualityRepository;

import jakarta.transaction.Transactional;


@Service
public class QualityService {

    @Autowired
    private QualityRepository qualityRepository;

    @Autowired
    private QualityValidaciones qualityValidaciones;


    @Transactional
    public List<QualityDTO> obtenerTodos(){
        List<Quality> lista = qualityRepository.findAll();
        List<QualityDTO> listaDTO = new ArrayList<>();
        for (Quality q : lista){
            listaDTO.add(convertirADTO(q));
        }
        return listaDTO;
    }

    @Transactional
    public QualityDTO obtenerPorId(Integer id){
        Quality q = qualityRepository.findById(id).orElse(null);
        return (q != null) ? convertirADTO(q) : null;
    }

    @Transactional
    public QualityDTO guardar(QualityDTO dto) {
        Quality q = convertirAEntidad(dto);
        Quality guardado = qualityRepository.save(q);
        return convertirADTO(guardado);
    }

    @Transactional
    public boolean eliminar(Integer Id){
        if (qualityRepository.existsById(Id)){
            qualityRepository.deleteById(Id);
            return true;
        }
        return false;
    }
    
    private QualityDTO convertirADTO(Quality q) {
        QualityDTO dto = new QualityDTO();
        dto.setIdQuality(q.getIdQuality());
        dto.setCantidadPh(q.getCantidadPh());
        dto.setControlQuality(q.isControlQuality());
        return dto;
    }

    private Quality convertirAEntidad(QualityDTO dto) {
    Quality q = new Quality();
    q.setIdQuality(dto.getIdQuality());
    q.setCantidadPh(dto.getCantidadPh());
    q.setControlQuality(dto.isControlQuality());
    return q;
    }
}
