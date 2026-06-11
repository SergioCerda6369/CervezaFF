package com.mantenimientos.mantenimientos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MantenimientosDTO {
    private Integer idMantenimiento;
    private String tipoEquipo;
    private String codigoEquipo;
    private String estadoEquipo;
    private String estadoMantenimiento;
    private Integer idFermentacion;
}
