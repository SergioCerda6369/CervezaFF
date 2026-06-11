package com.envasados.envasados.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "packing")
public class Packing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPacking;

    @NotBlank(message = "El tipo de envase es obligatorio.")
    @Size(min = 4, max = 255, message = "El tipo de envase debe tener entre 4 y 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String tipoEnvase;

    @NotNull(message = "La cantidad de envases es obligatoria.")
    @Min(value = 0, message = "La cantidad de envases debe de ser mayor o igual a 0.")
    @Column(nullable = false)
    private Integer cantidadEnvases;

}
