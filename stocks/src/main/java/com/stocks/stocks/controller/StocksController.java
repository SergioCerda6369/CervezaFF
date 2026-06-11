package com.stocks.stocks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stocks.stocks.dto.StocksDTO;
import com.stocks.stocks.model.Stocks;
import com.stocks.stocks.service.StocksService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/stocks")
public class StocksController {

    @Autowired
    private StocksService stocksService;

    @GetMapping
    public ResponseEntity<List<StocksDTO>> todas() {
        List<StocksDTO> lista = stocksService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            StocksDTO dto = stocksService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Stocks stock) {
        try {
            StocksDTO dto = stocksService.guardar(stock);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el stock de producto", HttpStatus.BAD_REQUEST);
        }
    }
}
