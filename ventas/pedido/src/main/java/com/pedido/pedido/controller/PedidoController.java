package com.pedido.pedido.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pedido.pedido.dto.PedidoDTO;
import com.pedido.pedido.model.Pedido;
import com.pedido.pedido.service.PedidoService;
import com.pedido.pedido.assembler.PedidoAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<PedidoDTO>>> todas() {
        List<EntityModel<PedidoDTO>> lista = pedidoService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                lista,
                linkTo(methodOn(PedidoController.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PedidoDTO>> porId(@PathVariable Integer id) {
        try {
            PedidoDTO dto = pedidoService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PedidoDTO>> registrar(@Valid @RequestBody Pedido pedido) {
        try {
            PedidoDTO dto = pedidoService.guardar(pedido);
            return ResponseEntity
                    .created(linkTo(methodOn(PedidoController.class).porId(dto.getIdPedido())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}