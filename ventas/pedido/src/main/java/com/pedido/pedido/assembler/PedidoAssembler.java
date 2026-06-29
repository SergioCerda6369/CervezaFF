package com.pedido.pedido.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pedido.pedido.controller.PedidoController;
import com.pedido.pedido.dto.PedidoDTO;

@Component
public class PedidoAssembler implements RepresentationModelAssembler<PedidoDTO, EntityModel<PedidoDTO>> {

    @Override
    public EntityModel<PedidoDTO> toModel(PedidoDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(PedidoController.class).porId(dto.getIdPedido())).withSelfRel(),
                linkTo(methodOn(PedidoController.class).todas()).withRel("pedidos")
        );
    }
}
