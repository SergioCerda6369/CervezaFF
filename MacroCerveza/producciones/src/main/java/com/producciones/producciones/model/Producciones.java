package com.producciones.producciones.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producciones")
public class Producciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion")
    private Integer idProduccion;

    @NotNull(message = "El ID de la receta es obligatorio")
    @Column(name = "id_receta", nullable = false)
    private Integer idReceta;

    @NotBlank(message = "El código de lote es obligatorio")
    @Column(name = "codigo_lote", nullable = false, length = 50)
    private String codigoLote;

    @NotNull(message = "La cantidad de litros a producir es obligatoria")
    @Min(value = 1, message = "Debe producir al menos 1 litro")
    @Column(name = "cantidad_litros", nullable = false)
    private Integer cantidadLitros;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotBlank(message = "El estado de la producción es obligatorio")
    @Column(name = "estado_produccion", nullable = false, length = 50)
    private String estadoProduccion;
}
