package com.calidades.calidades.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quality")
public class Quality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuality;

    @NotNull(message = "La cantidad de ph que tiene la cerveza es obligatoria.")
    @Min(value = 3, message = "La cantidad minima de PH que debe de tener la cerveza es de 3.0")
    @Max(value = 5, message = "La cantidad maxima de PH que debe de tener la cerveza es de 5.0")
    @Column(nullable = false)
    private Integer cantidadPh;

    @NotNull(message = "Se debe de saber si la cerveza paso o no el control de calidad.")
    @Column(nullable = false)
    private boolean controlQuality;

    
}
