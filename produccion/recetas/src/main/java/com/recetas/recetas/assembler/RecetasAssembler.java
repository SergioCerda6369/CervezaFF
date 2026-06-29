package com.recetas.recetas.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.recetas.recetas.controller.RecetasController;
import com.recetas.recetas.dto.RecetasDTO;

@Component
public class RecetasAssembler implements RepresentationModelAssembler<RecetasDTO, EntityModel<RecetasDTO>> {

    @Override
    public EntityModel<RecetasDTO> toModel(RecetasDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(RecetasController.class).porId(dto.getIdReceta())).withSelfRel(),
                linkTo(methodOn(RecetasController.class).todas()).withRel("recetas")
        );
    }
}