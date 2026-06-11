package com.materiales.materiales.model;

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
@Table(name = "material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMaterial;

    @NotBlank(message = "El nombre del material es obligatorio.")
    @Size(min = 3, max = 255, message = "El nombre del material debe tener entre 3 y 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String nombreMaterial;

    @NotNull(message = "La cantidad de stock del material es obligatoria.")
    @Min(value = 0, message = "La cantidad de stock debe de ser mayor a 0.")
    @Column(nullable = false)
    private Integer cantidadStock;

    @NotBlank(message = "El nombre del proveedor es obligatorio.")
    @Size(min = 15, max = 255, message = "El nombre del proveedor debe de tener entre 15 a 255 caracteres.")
    @Column(length = 255)
    private String nombreProveedor;

}
