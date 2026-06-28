package com.producciones.producciones.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    private Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {

        Integer idSimulado = faker.number().numberBetween(1, 100);
        Integer idRecetaSimulado = faker.number().numberBetween(1, 50);
        String codigoLoteSimulado = "LT-" + faker.number().numberBetween(1000, 9999);
        Integer cantidadLitrosSimulado = faker.number().numberBetween(100, 5000);
        LocalDate fechaInicioSimulado = LocalDate.now();
        String estadoProduccionSimulado = faker.options().option("Planificada", "En Progreso", "Terminada");

        Producciones produccionFalsa = Producciones.builder()
                .idProduccion(idSimulado)
                .idReceta(idRecetaSimulado)
                .codigoLote(codigoLoteSimulado)
                .cantidadLitros(cantidadLitrosSimulado)
                .fechaInicio(fechaInicioSimulado)
                .estadoProduccion(estadoProduccionSimulado)
                .build();

        RecetaExternoDTO recetaFalsa = new RecetaExternoDTO();
        recetaFalsa.setIdReceta(idRecetaSimulado);
        recetaFalsa.setNombreCerveza("Cerveza Rubia IPA");

        ProduccionesDTO dtoFalso = ProduccionesDTO.builder()
                .idProduccion(idSimulado)
                .idReceta(idRecetaSimulado)
                .codigoLote(codigoLoteSimulado)
                .cantidadLitros(cantidadLitrosSimulado)
                .fechaInicio(fechaInicioSimulado)
                .estadoProduccion(estadoProduccionSimulado)
                .receta(recetaFalsa)
                .build();

        when(produccionesRepository.findById(idSimulado)).thenReturn(Optional.of(produccionFalsa));
        when(produccionesValidaciones.convertirADTO(produccionFalsa)).thenReturn(dtoFalso);

        ProduccionesDTO resultado = produccionesService.buscarPorId(idSimulado);

        assertNotNull(resultado, "El DTO de producción no debería retornar nulo");
        assertEquals(idSimulado, resultado.getIdProduccion(), "El ID de producción debe coincidir");
        assertEquals(idRecetaSimulado, resultado.getIdReceta(), "El ID de la receta debe coincidir");
        assertEquals(codigoLoteSimulado, resultado.getCodigoLote(), "El código de lote debe coincidir");
        assertEquals(cantidadLitrosSimulado, resultado.getCantidadLitros(), "La cantidad de litros debe coincidir");
        assertEquals(fechaInicioSimulado, resultado.getFechaInicio(), "La fecha de inicio debe coincidir");
        assertEquals(estadoProduccionSimulado, resultado.getEstadoProduccion(), "El estado de producción debe coincidir");
        assertNotNull(resultado.getReceta(), "El objeto de la receta embebida externa no debe ser nulo");
        assertEquals("Cerveza Rubia IPA", resultado.getReceta().getNombreCerveza(), "El nombre de la receta debe coincidir");

        verify(produccionesRepository, times(1)).findById(idSimulado);
    }
}