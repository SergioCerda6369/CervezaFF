package com.fermentaciones.fermentaciones.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.controller.FermentationController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FermentationAssembler implements RepresentationModelAssembler<FermentationDTO, EntityModel<FermentationDTO>> {

    @Override
    public EntityModel<FermentationDTO> toModel(FermentationDTO fermentation) {
        return EntityModel.of(fermentation,
                linkTo(methodOn(FermentationController.class).porId(fermentation.getIdFermentation())).withSelfRel(),
                linkTo(methodOn(FermentationController.class).listar()).withRel("fermentaciones")
        );
    }

}
