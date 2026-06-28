package com.producciones.producciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.producciones.producciones.dto.ProduccionesDTO;
import com.producciones.producciones.dto.RecetaExternoDTO;
import com.producciones.producciones.model.Producciones;
import reactor.core.publisher.Mono;

@Service
public class ProduccionesValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public RecetaExternoDTO obtenerRecetaRemota(Integer idReceta) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://recetas-service/api/v1/recetas/" + idReceta)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(RecetaExternoDTO.class)
                    .block();
        } catch (Exception e) {
            RecetaExternoDTO fallback = new RecetaExternoDTO();
            fallback.setIdReceta(0);
            fallback.setNombreCerveza("DESCONOCIDA");
            fallback.setIngredientes("NO DISPONIBLES");
            return fallback;
        }
    }

    public ProduccionesDTO convertirADTO(Producciones produccion) {
        return ProduccionesDTO.builder()
                .idProduccion(produccion.getIdProduccion())
                .idReceta(produccion.getIdReceta())
                .codigoLote(produccion.getCodigoLote())
                .cantidadLitros(produccion.getCantidadLitros())
                .fechaInicio(produccion.getFechaInicio())
                .estadoProduccion(produccion.getEstadoProduccion())
                .receta(obtenerRecetaRemota(produccion.getIdReceta()))
                .build();
    }
}
