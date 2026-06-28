package com.producciones.producciones.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.producciones.producciones.controller.ProduccionesController;
import com.producciones.producciones.dto.ProduccionesDTO;

@Component
public class ProduccionesAssembler implements RepresentationModelAssembler<ProduccionesDTO, EntityModel<ProduccionesDTO>> {

    @Override
    public EntityModel<ProduccionesDTO> toModel(ProduccionesDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(ProduccionesController.class).porId(dto.getIdProduccion())).withSelfRel(),
                linkTo(methodOn(ProduccionesController.class).todas()).withRel("producciones")
        );
    }
}