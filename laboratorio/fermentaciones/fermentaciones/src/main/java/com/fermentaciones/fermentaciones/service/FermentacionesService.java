package com.fermentaciones.fermentaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.DTO.QualityExternoDTO;
import com.fermentaciones.fermentaciones.model.Fermentation;
import com.fermentaciones.fermentaciones.repository.FermentationRepository;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FermentacionesService {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    private FermentationRepository fermentationRepository;

    public FermentacionesService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public List<FermentationDTO> obtenerTodos() {
        List<FermentationDTO> listaDTOs = new ArrayList<>();
        List<Fermentation> fermentacionesReales = fermentationRepository.findAll();
        for (Fermentation f : fermentacionesReales) {
            listaDTOs.add(convertirADTO(f));
        }
        return listaDTOs;
    }

    public FermentationDTO buscarPorId(Integer id) {
        Fermentation f = fermentationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de fermentación no encontrado."));
        return convertirADTO(f);
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
        return convertirADTO(guardada);
    }

    private FermentationDTO convertirADTO(Fermentation fermentation) {
        FermentationDTO dto = new FermentationDTO();
        dto.setIdFermentation(fermentation.getIdFermentation());
        dto.setCodigoTanque(fermentation.getCodigoTanque());
        dto.setTemperaturaActual(fermentation.getTemperaturaActual());

        try {
            QualityExternoDTO qualityRecuperado = webClientBuilder.build()
                .get()
                .uri("http://127.0.0.1:8081/api/v1/calidades/fermentation/" + fermentation.getIdFermentation())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(QualityExternoDTO.class)
                .block();

            dto.setQuality(qualityRecuperado);
        } catch (Exception e) {
            dto.setQuality(null);
        }

        return dto;
    }
}
