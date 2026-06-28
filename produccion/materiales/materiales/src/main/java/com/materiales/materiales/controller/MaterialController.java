package com.materiales.materiales.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.materiales.materiales.DTO.MaterialDTO;
import com.materiales.materiales.model.Material;
import com.materiales.materiales.service.MaterialService;
import com.materiales.materiales.assembler.MaterialAssembler;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterialAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<MaterialDTO>>> listar() {
        List<EntityModel<MaterialDTO>> materiales = materialService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (materiales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                materiales,
                linkTo(methodOn(MaterialController.class).listar()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MaterialDTO>> porId(@PathVariable Integer id) {
        try {
            MaterialDTO dto = materialService.buscarPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MaterialDTO>> guardar(@Valid @RequestBody Material material) {
        try {
            MaterialDTO nuevo = materialService.guardar(material);
            return ResponseEntity
                    .created(linkTo(methodOn(MaterialController.class).porId(nuevo.getIdMaterial())).toUri())
                    .body(assembler.toModel(nuevo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (materialService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
