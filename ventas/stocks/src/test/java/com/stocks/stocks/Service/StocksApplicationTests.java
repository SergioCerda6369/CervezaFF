package com.stocks.stocks.Service;

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

import com.stocks.stocks.dto.StocksDTO;
import com.stocks.stocks.model.Stocks;
import com.stocks.stocks.repository.StocksRepository;
import com.stocks.stocks.service.StocksService;
import com.stocks.stocks.service.StocksValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class StocksApplicationTests {

    @Mock
    private StocksRepository stocksRepository;

    @Mock
    private StocksValidaciones stocksValidaciones;

    @InjectMocks
    private StocksService stocksService;

    private final Faker faker = new Faker();

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String nombreCervezaSimulado = faker.options().option("Ipa Galáctica", "Stout Imperial", "Golden Ale");
        Integer cantidadSimulado = faker.number().numberBetween(50, 1000);
        Double precioSimulado = faker.number().randomDouble(2, 2000, 5000);

        Stocks stockFalso = new Stocks();
        stockFalso.setIdStockFinal(idSimulado);
        stockFalso.setNombreCerveza(nombreCervezaSimulado);
        stockFalso.setCantidadDisponible(cantidadSimulado);
        stockFalso.setPrecioUnitario(precioSimulado);

        String conversionValida = String.valueOf(precioSimulado);
        StocksDTO dtoFalso = new StocksDTO();
        dtoFalso.setIdStockFinal(idSimulado);
        dtoFalso.setNombreCerveza(nombreCervezaSimulado);
        dtoFalso.setCantidadDisponible(cantidadSimulado);
        dtoFalso.setPrecioUnitario(Double.valueOf(conversionValida));

        when(stocksRepository.findById(idSimulado)).thenReturn(Optional.of(stockFalso));
        when(stocksValidaciones.convertirADto(stockFalso)).thenReturn(dtoFalso);

        StocksDTO resultado = stocksService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdStockFinal());
        assertEquals(nombreCervezaSimulado, resultado.getNombreCerveza());
        assertEquals(cantidadSimulado, resultado.getCantidadDisponible());
        assertEquals(Double.valueOf(conversionValida), resultado.getPrecioUnitario());

        verify(stocksRepository, times(1)).findById(idSimulado);
    }
}