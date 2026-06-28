package com.producciones.producciones.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.producciones.producciones.dto.ProduccionesDTO;
import com.producciones.producciones.model.Producciones;
import com.producciones.producciones.service.ProduccionesService;
import com.producciones.producciones.assembler.ProduccionesAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/producciones")
public class ProduccionesController {

    @Autowired
    private ProduccionesService produccionesService;

    @Autowired
    private ProduccionesAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProduccionesDTO>>> todas() {
        List<EntityModel<ProduccionesDTO>> lista = produccionesService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                lista,
                linkTo(methodOn(ProduccionesController.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProduccionesDTO>> porId(@PathVariable Integer id) {
        try {
            ProduccionesDTO dto = produccionesService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProduccionesDTO>> registrar(@Valid @RequestBody Producciones produccion) {
        try {
            ProduccionesDTO dto = produccionesService.guardar(produccion);
            return ResponseEntity
                    .created(linkTo(methodOn(ProduccionesController.class).porId(dto.getIdProduccion())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}