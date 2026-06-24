package com.producciones.producciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.producciones.producciones.dto.ProduccionesDTO;
import com.producciones.producciones.dto.RecetaExternoDTO;
import com.producciones.producciones.model.Producciones;
import com.producciones.producciones.repository.ProduccionesRepository;

import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProduccionesService {

    private final WebClient.Builder webClientBuilder;

    @Autowired
    private ProduccionesRepository produccionesRepository;

    public ProduccionesService(WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
    }

    public List<ProduccionesDTO> obtenerTodos() {
        List<ProduccionesDTO> listaDTOs = new ArrayList<>();
        List<Producciones> produccionesReales = produccionesRepository.findAll();
        for (Producciones p : produccionesReales) {
            listaDTOs.add(convertirADTO(p));
        }
        return listaDTOs;
    }

    public ProduccionesDTO buscarPorId(Integer id) {
        Producciones produccion = produccionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La orden de producción con ID " + id + " no existe."));
        return convertirADTO(produccion);
    }

    public ProduccionesDTO guardar(Producciones produccion) {
        Producciones produccionGuardada = produccionesRepository.save(produccion);
        return convertirADTO(produccionGuardada);
    }

    private ProduccionesDTO convertirADTO(Producciones produccion) {
        ProduccionesDTO dto = ProduccionesDTO.builder()
            .idProduccion(produccion.getIdProduccion())
            .idReceta(produccion.getIdReceta())
            .cantidadLitros(produccion.getCantidadLitros())
            .estadoProduccion(produccion.getEstadoProduccion())
            .build();

        try {
            RecetaExternoDTO recetaRecuperada = webClientBuilder.build()
                .get()
                .uri("http://127.0.0.1:8089/api/v1/recetas/" + produccion.getIdReceta())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(RecetaExternoDTO.class)
                .block();

            dto.setReceta(recetaRecuperada);    
        } catch (Exception e) {
            dto.setReceta(null);
        }
        return dto;
    }    
}