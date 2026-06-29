package com.pedido.pedido.Service;

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

import com.pedido.pedido.dto.DespachoExternoDTO;
import com.pedido.pedido.dto.PedidoDTO;
import com.pedido.pedido.dto.StockExternoDTO;
import com.pedido.pedido.model.Pedido;
import com.pedido.pedido.repository.PedidoRepository;
import com.pedido.pedido.service.PedidoService;
import com.pedido.pedido.service.PedidoValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class PedidoApplicationTests {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoValidaciones pedidoValidaciones;

    @InjectMocks
    private PedidoService pedidoService;

    private final Faker faker = new Faker();

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String clienteSimulado = faker.company().name();
        Integer idStockSimulado = faker.number().numberBetween(1, 50);
        Integer cantidadSimulada = faker.number().numberBetween(10, 500);
        Double totalSimulado = faker.number().randomDouble(2, 50000, 300000);
        String estadoSimulado = faker.options().option("Pendiente", "Procesado", "Enviado");

        Pedido pedidoFalso = new Pedido();
        pedidoFalso.setIdPedido(idSimulado);
        pedidoFalso.setCliente(clienteSimulado);
        pedidoFalso.setIdStockFinal(idStockSimulado);
        pedidoFalso.setCantidadSolicitada(cantidadSimulada);
        pedidoFalso.setTotalVenta(totalSimulado);
        pedidoFalso.setEstadoPedido(estadoSimulado);

        DespachoExternoDTO despachoFalso = new DespachoExternoDTO();
        despachoFalso.setIdDispatch(1);
        despachoFalso.setEstadoDispatch("PROGRAMADO");

        StockExternoDTO stockFalso = new StockExternoDTO();
        stockFalso.setIdStockFinal(idStockSimulado);
        stockFalso.setNombreCerveza("Cerveza Ipa Galáctica");

        PedidoDTO dtoFalso = new PedidoDTO();
        dtoFalso.setIdPedido(idSimulado);
        dtoFalso.setCliente(clienteSimulado);
        dtoFalso.setIdStockFinal(idStockSimulado);
        dtoFalso.setCantidadSolicitada(cantidadSimulada);
        dtoFalso.setTotalVenta(totalSimulado);
        dtoFalso.setEstadoPedido(estadoSimulado);
        dtoFalso.setDespacho(despachoFalso);
        dtoFalso.setStock(stockFalso);

        when(pedidoRepository.findById(idSimulado)).thenReturn(Optional.of(pedidoFalso));
        when(pedidoValidaciones.convertirADTO(pedidoFalso)).thenReturn(dtoFalso);

        PedidoDTO resultado = pedidoService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdPedido());
        assertEquals(clienteSimulado, resultado.getCliente());
        assertEquals(idStockSimulado, resultado.getIdStockFinal());
        assertEquals(cantidadSimulada, resultado.getCantidadSolicitada());
        assertEquals(totalSimulado, resultado.getTotalVenta());
        assertEquals(estadoSimulado, resultado.getEstadoPedido());
        assertNotNull(resultado.getStock());
        assertEquals("Cerveza Ipa Galáctica", resultado.getStock().getNombreCerveza());

        verify(pedidoRepository, times(1)).findById(idSimulado);
    }
}