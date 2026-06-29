package com.despachos.despachos.model;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dispatch")
public class Dispatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDispatch;

    @NotBlank(message = "La patente del camión es obligatoria.")
    @Size(min = 6, max = 6, message = "La patente del camión debe de tener 6 caracteres.")
    @Column(nullable = false, length = 6)
    private String patenteCamion;

    @NotBlank(message = "El nombre del conductor es obligatorio.")
    @Size(min = 3, max = 255, message = "El nombre del conductor debe de tener entre 3 y 255 caracteres.")
    @Column(nullable = false, length = 255)
    private String nombreConductor;

    @NotBlank(message = "El estado de la distribución es obligatorio.")
    @Size(min = 2, max = 100, message = "El estado de la distribución debe de tener entre 2 y 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String estadoDispatch;

    @NotNull(message = "El ID del pedido es obligatorio.")
    @Column(name = "id_pedido", nullable = false)
    private Integer idPedido;

}
