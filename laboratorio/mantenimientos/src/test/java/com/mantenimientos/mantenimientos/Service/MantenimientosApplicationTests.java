package com.mantenimientos.mantenimientos.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mantenimientos.mantenimientos.dto.MantenimientosDTO;
import com.mantenimientos.mantenimientos.model.Mantenimientos;
import com.mantenimientos.mantenimientos.repository.MantenimientosRepository;
import com.mantenimientos.mantenimientos.service.MantenimientosService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class MantenimientosApplicationTests {

    @Mock
    private MantenimientosRepository mantenimientosRepository;

    @InjectMocks
    private MantenimientosService mantenimientosService;

    private Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String tipoEquipoSimulado = faker.options().option("Tanque de Fermentación", "Bomba de Agua", "Enfriador");
        String codigoEquipoSimulado = "EQ-" + faker.number().numberBetween(100, 999);
        String estadoEquipoSimulado = faker.options().option("Operativo", "En Revisión", "Crítico");
        String estadoMantenimientoSimulado = faker.options().option("Pendiente", "En Proceso", "Completado");
        Integer idFermentationSimulado = faker.number().numberBetween(1, 50);

        Mantenimientos mantenimientoFalso = Mantenimientos.builder()
                .idMantenimiento(idSimulado)
                .tipoEquipo(tipoEquipoSimulado)
                .codigoEquipo(codigoEquipoSimulado)
                .estadoEquipo(estadoEquipoSimulado)
                .estadoMantenimiento(estadoMantenimientoSimulado)
                .idFermentation(idFermentationSimulado)
                .build();

        when(mantenimientosRepository.findById(idSimulado)).thenReturn(Optional.of(mantenimientoFalso));

        MantenimientosDTO resultado = mantenimientosService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdMantenimiento());
        assertEquals(tipoEquipoSimulado, resultado.getTipoEquipo());
        assertEquals(codigoEquipoSimulado, resultado.getCodigoEquipo());
        assertEquals(estadoEquipoSimulado, resultado.getEstadoEquipo());
        assertEquals(estadoMantenimientoSimulado, resultado.getEstadoMantenimiento());
        assertEquals(idFermentationSimulado, resultado.getIdFermentacion());

        verify(mantenimientosRepository, times(1)).findById(idSimulado);
    }
}