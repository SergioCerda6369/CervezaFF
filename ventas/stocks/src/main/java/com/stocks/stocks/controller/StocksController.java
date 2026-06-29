package com.stocks.stocks.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stocks.stocks.dto.StocksDTO;
import com.stocks.stocks.model.Stocks;
import com.stocks.stocks.service.StocksService;
import com.stocks.stocks.assembler.StocksAssembler;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/stocks")
public class StocksController {

    @Autowired
    private StocksService stocksService;

    @Autowired
    private StocksAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<StocksDTO>>> todas() {
        List<EntityModel<StocksDTO>> lista = stocksService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                lista,
                linkTo(methodOn(StocksController.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<StocksDTO>> porId(@PathVariable Integer id) {
        try {
            StocksDTO dto = stocksService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<StocksDTO>> registrar(@Valid @RequestBody Stocks stock) {
        try {
            StocksDTO dto = stocksService.guardar(stock);
            return ResponseEntity
                    .created(linkTo(methodOn(StocksController.class).porId(dto.getIdStockFinal())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
