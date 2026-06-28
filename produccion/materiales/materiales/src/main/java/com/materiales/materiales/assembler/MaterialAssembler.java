package com.materiales.materiales.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.materiales.materiales.controller.MaterialController;
import com.materiales.materiales.DTO.MaterialDTO;

@Component
public class MaterialAssembler implements RepresentationModelAssembler<MaterialDTO, EntityModel<MaterialDTO>> {

    @Override
    public EntityModel<MaterialDTO> toModel(MaterialDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(MaterialController.class).porId(dto.getIdMaterial())).withSelfRel(),
                linkTo(methodOn(MaterialController.class).listar()).withRel("materiales")
        );
    }
}