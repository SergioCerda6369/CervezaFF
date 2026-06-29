package com.despachos.despachos.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.despachos.despachos.DTO.DispatchDTO;
import com.despachos.despachos.model.Dispatch;
import com.despachos.despachos.service.DispatchService;
import com.despachos.despachos.assembler.DispatchAssembler;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/despachos")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    @Autowired
    private DispatchAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<DispatchDTO>>> listar() {
        List<EntityModel<DispatchDTO>> despachos = dispatchService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (despachos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                despachos,
                linkTo(methodOn(DispatchController.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DispatchDTO>> porId(@PathVariable Integer id) {
        try {
            DispatchDTO dto = dispatchService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/pedido/{idPedido}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DispatchDTO>> buscarPorPedido(@PathVariable Integer idPedido) {
        DispatchDTO dto = dispatchService.buscarPorIdPedido(idPedido); 
        if (dto != null) {
            return ResponseEntity.ok(assembler.toModel(dto));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<DispatchDTO>> guardar(@Valid @RequestBody Dispatch dispatch) {
        try {
            DispatchDTO nuevo = dispatchService.guardar(dispatch);
            return ResponseEntity
                    .created(linkTo(methodOn(DispatchController.class).porId(nuevo.getIdDispatch())).toUri())
                    .body(assembler.toModel(nuevo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (dispatchService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}