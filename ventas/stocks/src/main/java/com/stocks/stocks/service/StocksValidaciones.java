package com.stocks.stocks.service;

import org.springframework.stereotype.Service;
import com.stocks.stocks.dto.StocksDTO;
import com.stocks.stocks.model.Stocks;

@Service
public class StocksValidaciones {

    public StocksDTO convertirADto(Stocks stock) {
        return StocksDTO.builder()
                .idStockFinal(stock.getIdStockFinal())
                .nombreCerveza(stock.getNombreCerveza())
                .cantidadDisponible(stock.getCantidadDisponible())
                .precioUnitario(stock.getPrecioUnitario())
                .build();
    }
}