package com.despachos.despachos.service;

import org.springframework.stereotype.Service;
import com.despachos.despachos.DTO.DispatchDTO;
import com.despachos.despachos.model.Dispatch;

@Service
public class DispatchValidaciones {

    public DispatchDTO convertirADTO(Dispatch d) {
        DispatchDTO dto = new DispatchDTO();
        dto.setIdDispatch(d.getIdDispatch());
        dto.setPatenteCamion(d.getPatenteCamion());
        dto.setNombreConductor(d.getNombreConductor());
        dto.setEstadoDispatch(d.getEstadoDispatch());
        dto.setIdPedido(d.getIdPedido());
        return dto;
    }
}