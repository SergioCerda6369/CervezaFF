package com.fermentaciones.fermentaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.DTO.QualityExternoDTO;
import com.fermentaciones.fermentaciones.model.Fermentation;
import reactor.core.publisher.Mono;

@Service
public class FermentationValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public QualityExternoDTO obtenerQuality(Integer idFermentation) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://calidades-service/api/v1/calidades/fermentation/" + idFermentation)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(QualityExternoDTO.class)
                    .block();
        } catch (Exception e) {
            QualityExternoDTO fallback = new QualityExternoDTO();
            fallback.setIdQuality(0);
            fallback.setCantidadPh(0);
            fallback.setControlQuality(false);
            return fallback;
        }
    }

    public FermentationDTO convertirADTO(Fermentation fermentation) {
        FermentationDTO dto = new FermentationDTO();
        dto.setIdFermentation(fermentation.getIdFermentation());
        dto.setCodigoTanque(fermentation.getCodigoTanque());
        dto.setTemperaturaActual(fermentation.getTemperaturaActual());
        dto.setQuality(obtenerQuality(fermentation.getIdFermentation()));
        return dto;
    }
}
