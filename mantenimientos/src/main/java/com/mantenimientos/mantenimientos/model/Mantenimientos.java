package com.mantenimientos.mantenimientos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mantenimiento")
public class Mantenimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mantenimiento")
    private Integer idMantenimiento;

    @NotBlank(message = "El tipo de equipo es obligatorio.")
    @Size(min = 3, max = 255, message = "El tipo de equipo debe de tener entre 3 y 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String tipoEquipo;

    @NotBlank(message = "El CODIGO del equipo al que se le hara mantenimiento es obligatorio.")
    @Size(min = 3, max = 10, message = "El CODIGO del equipo debe de tener entre 3 y 10 caracteres. (Ejemplo: A-01, B-02, etc.)")
    @Column(nullable = false, length = 10)
    private String codigoEquipo;

    @NotBlank(message = "El estado del equipo es obligatorio.")
    @Size(min = 3, max = 255, message = "El estado del equipo debe de tener entre 3 y 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String estadoEquipo;

    @NotBlank(message = "El estado del mantenimiento es obligatorio.")
    @Size(min = 3, max = 255, message = "El estado del mantenimiento debe de tener entre 3 y 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String estadoMantenimiento;

    // Rompemos la relación ManyToOne y dejamos solo el ID de referencia del tanque
    @NotNull(message = "El ID del tanque de fermentación es obligatorio.")
    @Column(name = "id_fermentacion", nullable = false)
    private Integer idFermentacion;
}
