package com.envasados.envasados.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.envasados.envasados.controller.PackingController;
import com.envasados.envasados.DTO.PackingDTO;

@Component
public class PackingAssembler implements RepresentationModelAssembler<PackingDTO, EntityModel<PackingDTO>> {

    @Override
    public EntityModel<PackingDTO> toModel(PackingDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PackingController.class).porId(dto.getIdPacking())).withSelfRel(),
                linkTo(methodOn(PackingController.class).listar()).withRel("envasados")
        );
    }
}
