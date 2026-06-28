package com.mantenimientos.mantenimientos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.mantenimientos.mantenimientos.dto.MantenimientosDTO;
import com.mantenimientos.mantenimientos.dto.FermentationExternoDTO;
import com.mantenimientos.mantenimientos.model.Mantenimientos;
import reactor.core.publisher.Mono;

@Service
public class MantenimientosValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public FermentationExternoDTO obtenerFermentation(Integer idFermentation) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://fermentaciones-service/api/v1/fermentaciones/" + idFermentation)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(FermentationExternoDTO.class)
                    .block();
        } catch (Exception e) {
            FermentationExternoDTO fallback = new FermentationExternoDTO();
            fallback.setIdFermentation(0);
            fallback.setCodigoTanque("DESCONOCIDO");
            fallback.setTemperaturaActual(0);
            return fallback;
        }
    }

    public MantenimientosDTO convertirADto(Mantenimientos mantenimiento) {
        return MantenimientosDTO.builder()
                .idMantenimiento(mantenimiento.getIdMantenimiento())
                .tipoEquipo(mantenimiento.getTipoEquipo())
                .codigoEquipo(mantenimiento.getCodigoEquipo())
                .estadoEquipo(mantenimiento.getEstadoEquipo())
                .estadoMantenimiento(mantenimiento.getEstadoMantenimiento())
                .idFermentacion(mantenimiento.getIdFermentation())
                .fermentation(obtenerFermentation(mantenimiento.getIdFermentation()))
                .build();
    }
}