package com.envasados.envasados.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.envasados.envasados.DTO.PackingDTO;
import com.envasados.envasados.model.Packing;
import com.envasados.envasados.service.PackingService;
import com.envasados.envasados.assembler.PackingAssembler;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/envasados")
public class PackingController {

    @Autowired
    private PackingService packingService;

    @Autowired
    private PackingAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<PackingDTO>>> listar() {
        List<EntityModel<PackingDTO>> envasados = packingService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (envasados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                envasados,
                linkTo(methodOn(PackingController.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PackingDTO>> porId(@PathVariable Integer id) {
        try {
            PackingDTO dto = packingService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<PackingDTO>> guardar(@Valid @RequestBody Packing packing) {
        try {
            PackingDTO nuevo = packingService.guardar(packing);
            return ResponseEntity
                    .created(linkTo(methodOn(PackingController.class).porId(nuevo.getIdPacking())).toUri())
                    .body(assembler.toModel(nuevo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (packingService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}