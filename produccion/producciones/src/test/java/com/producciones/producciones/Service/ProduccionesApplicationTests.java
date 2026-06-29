package com.producciones.producciones.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.producciones.producciones.dto.ProduccionesDTO;
import com.producciones.producciones.dto.RecetaExternoDTO;
import com.producciones.producciones.model.Producciones;
import com.producciones.producciones.repository.ProduccionesRepository;
import com.producciones.producciones.service.ProduccionesService;
import com.producciones.producciones.service.ProduccionesValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class ProduccionesApplicationTests {

    @Mock
    private ProduccionesRepository produccionesRepository;

    @Mock
    private ProduccionesValidaciones produccionesValidaciones; 

    @InjectMocks
    private ProduccionesService produccionesService;

    private final Faker faker = new Faker();

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        Integer idRecetaSimulado = faker.number().numberBetween(1, 50);
        String codigoLoteSimulado = "LT-" + faker.number().numberBetween(1000, 9999);
        Integer cantidadLitrosSimulado = faker.number().numberBetween(100, 5000);
        LocalDate fechaInicioSimulado = LocalDate.now();
        String estadoProduccionSimulado = faker.options().option("Planificada", "En Progreso", "Terminada");

        Producciones produccionFalsa = new Producciones();
        produccionFalsa.setIdProduccion(idSimulado);
        produccionFalsa.setIdReceta(idRecetaSimulado);
        produccionFalsa.setCodigoLote(codigoLoteSimulado);
        produccionFalsa.setCantidadLitros(cantidadLitrosSimulado);
        produccionFalsa.setFechaInicio(fechaInicioSimulado);
        produccionFalsa.setEstadoProduccion(estadoProduccionSimulado);

        RecetaExternoDTO recetaFalsa = new RecetaExternoDTO();
        recetaFalsa.setIdReceta(idRecetaSimulado);
        recetaFalsa.setNombreCerveza("Cerveza Rubia IPA");

        ProduccionesDTO dtoFalso = new ProduccionesDTO();
        dtoFalso.setIdProduccion(idSimulado);
        dtoFalso.setIdReceta(idRecetaSimulado);
        dtoFalso.setCodigoLote(codigoLoteSimulado);
        dtoFalso.setCantidadLitros(cantidadLitrosSimulado);
        dtoFalso.setFechaInicio(fechaInicioSimulado);
        dtoFalso.setEstadoProduccion(estadoProduccionSimulado);
        dtoFalso.setReceta(recetaFalsa);

        when(produccionesRepository.findById(idSimulado)).thenReturn(Optional.of(produccionFalsa));
        when(produccionesValidaciones.convertirADTO(produccionFalsa)).thenReturn(dtoFalso);

        ProduccionesDTO resultado = produccionesService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdProduccion());
        assertEquals(idRecetaSimulado, resultado.getIdReceta());
        assertEquals(codigoLoteSimulado, resultado.getCodigoLote());
        assertEquals(cantidadLitrosSimulado, resultado.getCantidadLitros());
        assertEquals(fechaInicioSimulado, resultado.getFechaInicio());
        assertEquals(estadoProduccionSimulado, resultado.getEstadoProduccion());
        assertNotNull(resultado.getReceta());
        assertEquals("Cerveza Rubia IPA", resultado.getReceta().getNombreCerveza());

        verify(produccionesRepository, times(1)).findById(idSimulado);
    }
}
