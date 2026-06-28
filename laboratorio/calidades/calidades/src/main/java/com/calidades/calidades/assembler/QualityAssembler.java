package com.calidades.calidades.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.calidades.calidades.controller.QualityController;
import com.calidades.calidades.DTO.QualityDTO;

@Component
public class QualityAssembler implements RepresentationModelAssembler<QualityDTO, EntityModel<QualityDTO>> {

    @Override
    public EntityModel<QualityDTO> toModel(QualityDTO quality) {
        return EntityModel.of(quality,
                linkTo(methodOn(QualityController.class).buscarPorFermentation(quality.getIdQuality())).withSelfRel()
        );
    }
}
