package com.envasados.envasados.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.envasados.envasados.DTO.PackingDTO;
import com.envasados.envasados.model.Packing;
import com.envasados.envasados.repository.PackingRepository;
import com.envasados.envasados.service.PackingService;
import com.envasados.envasados.service.PackingValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class PackingApplicationTests {

    @Mock
    private PackingRepository packingRepository;

    @Mock
    private PackingValidaciones packingValidaciones;

    @InjectMocks
    private PackingService packingService;

    private final Faker faker = new Faker();

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String tipoEnvaseSimulado = faker.options().option("Barril Kornelius 50L", "Lata Alumino 473cc", "Botella Vidrio 500cc");
        Integer cantidadSimulada = faker.number().numberBetween(100, 2000);

        Packing packingFalso = new Packing();
        packingFalso.setIdPacking(idSimulado);
        packingFalso.setTipoEnvase(tipoEnvaseSimulado);
        packingFalso.setCantidadEnvases(cantidadSimulada);

        PackingDTO dtoFalso = new PackingDTO();
        dtoFalso.setIdPacking(idSimulado);
        dtoFalso.setTipoEnvase(tipoEnvaseSimulado);
        dtoFalso.setCantidadEnvases(cantidadSimulada);

        when(packingRepository.findById(idSimulado)).thenReturn(Optional.of(packingFalso));
        when(packingValidaciones.convertirADTO(packingFalso)).thenReturn(dtoFalso);

        PackingDTO resultado = packingService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdPacking());
        assertEquals(tipoEnvaseSimulado, resultado.getTipoEnvase());
        assertEquals(cantidadSimulada, resultado.getCantidadEnvases());

        verify(packingRepository, times(1)).findById(idSimulado);
    }
}