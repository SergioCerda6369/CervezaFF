package com.recetas.recetas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recetas.recetas.dto.RecetasDTO;
import com.recetas.recetas.model.Recetas;
import com.recetas.recetas.service.RecetasService;
import com.recetas.recetas.assembler.RecetasAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/recetas")
public class RecetasController {

    @Autowired
    private RecetasService recetasService;

    @Autowired
    private RecetasAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<RecetasDTO>>> todas() {
        List<EntityModel<RecetasDTO>> lista = recetasService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                lista,
                linkTo(methodOn(RecetasController.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<RecetasDTO>> porId(@PathVariable Integer id) {
        try {
            RecetasDTO dto = recetasService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<RecetasDTO>> registrar(@Valid @RequestBody Recetas receta) {
        try {
            RecetasDTO dto = recetasService.guardar(receta);
            return ResponseEntity
                    .created(linkTo(methodOn(RecetasController.class).porId(dto.getIdReceta())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}