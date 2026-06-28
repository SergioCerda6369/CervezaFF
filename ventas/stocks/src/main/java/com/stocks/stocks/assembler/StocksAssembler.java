package com.stocks.stocks.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.stocks.stocks.controller.StocksController;
import com.stocks.stocks.dto.StocksDTO;

@Component
public class StocksAssembler implements RepresentationModelAssembler<StocksDTO, EntityModel<StocksDTO>> {

    @Override
    public EntityModel<StocksDTO> toModel(StocksDTO dto) {
        return EntityModel.of(dto,
                linkTo(methodOn(StocksController.class).porId(dto.getIdStockFinal())).withSelfRel(),
                linkTo(methodOn(StocksController.class).todas()).withRel("stocks")
        );
    }
}