package com.calidades.calidades.controller;


import org.springframework.beans.factory.annotation.Autowired;
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

import com.calidades.calidades.DTO.QualityDTO;
import com.calidades.calidades.assembler.QualityAssembler;
import com.calidades.calidades.service.QualityService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController("qualityControllerV2")
@RequestMapping("/api/calidades")
public class QualityController {

    @Autowired
    private QualityService qualityService;

    @Autowired
    private QualityAssembler assembler;
    
    
    @GetMapping("/fermentation/{idFermentation}") 
    public ResponseEntity<QualityDTO> buscarPorFermentation(@PathVariable Integer idFermentation) {
        QualityDTO dto = qualityService.obtenerPorId(idFermentation);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<QualityDTO>> guardar(@Valid @RequestBody QualityDTO qualityDTO) {
        try {
            QualityDTO guardado = qualityService.guardar(qualityDTO);
            return ResponseEntity
                    .created(linkTo(methodOn(QualityController.class).buscarPorFermentation(guardado.getIdQuality())).toUri())
                    .body(assembler.toModel(guardado));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (qualityService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
