package com.producciones.producciones.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProduccionesDTO {
    private Integer idProduccion;
    private Integer idReceta;
    private String codigoLote;
    private Integer cantidadLitros;
    private LocalDate fechaInicio;
    private String estadoProduccion;
}
