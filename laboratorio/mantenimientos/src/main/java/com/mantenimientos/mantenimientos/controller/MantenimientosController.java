package com.mantenimientos.mantenimientos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mantenimientos.mantenimientos.dto.MantenimientosDTO;
import com.mantenimientos.mantenimientos.model.Mantenimientos;
import com.mantenimientos.mantenimientos.service.MantenimientosService;
import com.mantenimientos.mantenimientos.assembler.MantenimientosAssembler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("mantenimientosControllerV2")
@RequestMapping("/api/v1/mantenimientos")
public class MantenimientosController {

    @Autowired
    private MantenimientosService mantenimientosService;

    @Autowired
    private MantenimientosAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<MantenimientosDTO>>> todas() {
        List<EntityModel<MantenimientosDTO>> lista = mantenimientosService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                lista,
                linkTo(methodOn(MantenimientosController.class).todas()).withSelfRel()
        ));
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MantenimientosDTO>> porId(@PathVariable Integer id) {
        try {
            MantenimientosDTO dto = mantenimientosService.buscarPorId(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<MantenimientosDTO>> registrar(@Valid @RequestBody Mantenimientos mantenimiento) {
        try {
            MantenimientosDTO dto = mantenimientosService.guardar(mantenimiento);
            return ResponseEntity
                    .created(linkTo(methodOn(MantenimientosController.class).porId(dto.getIdMantenimiento())).toUri())
                    .body(assembler.toModel(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
