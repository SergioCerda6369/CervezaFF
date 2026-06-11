package com.stocks.stocks.model;


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
@Table(name = "stock_final")
public class Stocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stock_final")
    private Integer idStockFinal;

    @NotBlank(message = "El nombre de la cerveza es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombreCerveza;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(0)
    @Column(name = "cantidad_disponible", nullable = false)
    private Integer cantidadDisponible;

    @NotNull(message = "El precio es obligatorio")
    @Min(0)
    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

}
