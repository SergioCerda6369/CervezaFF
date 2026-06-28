package com.recetas.recetas.model;

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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recetas")
public class Recetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_receta")
    private Integer idReceta;

    @NotBlank(message = "El nombre de la receta/cerveza es obligatorio")
    @Column(name = "nombre_cerveza", nullable = false, length = 100)
    private String nombreCerveza;

    @NotBlank(message = "La descripción de la receta no puede estar vacía")
    @Column(nullable = false, length = 255)
    private String descripcion;

    @NotBlank(message = "Debe especificar los ingredientes principales (Malta, Lúpulo, Levadura)")
    @Column(nullable = false, length = 255)
    private String ingredientes;

    @NotNull(message = "El tiempo de cocción es obligatorio")
    @Min(value = 1, message = "El tiempo de cocción debe ser de al menos 1 minuto")
    @Column(name = "tiempo_coccion_minutos", nullable = false)
    private Integer tiempoCoccionMinutos;

    @NotNull(message = "Los días estimados de fermentación son obligatorios")
    @Min(value = 1, message = "La fermentación requiere al menos 1 día")
    @Column(name = "dias_fermentacion", nullable = false)
    private Integer diasFermentacion;

}
