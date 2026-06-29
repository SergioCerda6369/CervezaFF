package com.despachos.despachos.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.despachos.despachos.controller.DispatchController;
import com.despachos.despachos.DTO.DispatchDTO;

@Component
public class DispatchAssembler implements RepresentationModelAssembler<DispatchDTO, EntityModel<DispatchDTO>> {

    @Override
    public EntityModel<DispatchDTO> toModel(DispatchDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(DispatchController.class).porId(dto.getIdDispatch())).withSelfRel(),
                linkTo(methodOn(DispatchController.class).buscarPorPedido(dto.getIdPedido())).withRel("pedido_asociado")
        );
    }
}