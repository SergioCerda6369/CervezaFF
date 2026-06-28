package com.mantenimientos.mantenimientos.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.mantenimientos.mantenimientos.controller.MantenimientosController;
import com.mantenimientos.mantenimientos.dto.MantenimientosDTO;

@Component
public class MantenimientosAssembler implements RepresentationModelAssembler<MantenimientosDTO, EntityModel<MantenimientosDTO>> {

    @Override
    public EntityModel<MantenimientosDTO> toModel(MantenimientosDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(MantenimientosController.class).porId(dto.getIdMantenimiento())).withSelfRel(),
                linkTo(methodOn(MantenimientosController.class).todas()).withRel("mantenimientos")
        );
    }
}