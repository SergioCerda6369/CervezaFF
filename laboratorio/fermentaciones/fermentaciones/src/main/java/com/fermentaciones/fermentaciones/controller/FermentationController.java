package com.fermentaciones.fermentaciones.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.assembler.FermentationAssembler;
import com.fermentaciones.fermentaciones.model.Fermentation;
import com.fermentaciones.fermentaciones.service.FermentacionesService;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("fermentationControllerV2")
@RequestMapping("/api/fermentaciones")
public class FermentationController {

    @Autowired
    private FermentacionesService fermentationService;

    @Autowired
    private FermentationAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<FermentationDTO>>> listar() {
        List<EntityModel<FermentationDTO>> fermentaciones = fermentationService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (fermentaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                fermentaciones,
                linkTo(methodOn(FermentationController.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<FermentationDTO>> porId(@PathVariable Integer id) {
        try {
            FermentationDTO dto = fermentationService.buscarPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<FermentationDTO>> guardar(@Valid @RequestBody Fermentation fermentacion) {
        try {
            FermentationDTO nuevaFer = fermentationService.guardar(fermentacion);
            return ResponseEntity
                    .created(linkTo(methodOn(FermentationController.class).porId(nuevaFer.getIdFermentation())).toUri())
                    .body(assembler.toModel(nuevaFer));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (fermentationService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}