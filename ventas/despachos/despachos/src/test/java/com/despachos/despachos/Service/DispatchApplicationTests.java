package com.despachos.despachos.Service;

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

import com.despachos.despachos.DTO.DispatchDTO;
import com.despachos.despachos.model.Dispatch;
import com.despachos.despachos.repository.DispatchRepository;
import com.despachos.despachos.service.DispatchService;
import com.despachos.despachos.service.DispatchValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class DispatchApplicationTests {

    @Mock
    private DispatchRepository dispatchRepository;

    @Mock
    private DispatchValidaciones dispatchValidaciones;

    @InjectMocks
    private DispatchService dispatchService;

    private final Faker faker = new Faker();

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String patenteSimulada = "AA" + faker.number().numberBetween(10, 99) + "BB";
        String conductorSimulado = faker.name().fullName();
        String estadoSimulado = faker.options().option("Preparado", "En Ruta", "Entregado");
        Integer idPedidoSimulado = faker.number().numberBetween(1, 500);

        Dispatch dispatchFalso = new Dispatch(idSimulado, patenteSimulada, conductorSimulado, estadoSimulado, idPedidoSimulado);
        DispatchDTO dtoFalso = new DispatchDTO(idSimulado, patenteSimulada, conductorSimulado, estadoSimulado, idPedidoSimulado);

        when(dispatchRepository.findById(idSimulado)).thenReturn(Optional.of(dispatchFalso));
        when(dispatchValidaciones.convertirADTO(dispatchFalso)).thenReturn(dtoFalso);

        DispatchDTO resultado = dispatchService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdDispatch());
        assertEquals(patenteSimulada, resultado.getPatenteCamion());
        assertEquals(conductorSimulado, resultado.getNombreConductor());
        assertEquals(estadoSimulado, resultado.getEstadoDispatch());
        assertEquals(idPedidoSimulado, resultado.getIdPedido());

        verify(dispatchRepository, times(1)).findById(idSimulado);
    }
}