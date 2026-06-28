package com.stocks.stocks.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stocks.stocks.dto.StocksDTO;
import com.stocks.stocks.model.Stocks;
import com.stocks.stocks.repository.StocksRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StocksService {

    @Autowired
    private StocksRepository stocksRepository;

    @Autowired
    private StocksValidaciones validaciones;

    public List<StocksDTO> obtenerTodos() {
        return stocksRepository.findAll().stream()
                .map(validaciones::convertirADto)
                .collect(Collectors.toList());
    }

    public StocksDTO buscarPorId(Integer id) {
        Stocks stock = stocksRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El registro de stock con ID " + id + " no existe."));
        return validaciones.convertirADto(stock);
    }

    public StocksDTO guardar(Stocks stock) {
        Stocks stockGuardado = stocksRepository.save(stock);
        return validaciones.convertirADto(stockGuardado);
    }
}