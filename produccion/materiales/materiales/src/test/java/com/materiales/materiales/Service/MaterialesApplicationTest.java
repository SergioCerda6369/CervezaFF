package com.materiales.materiales.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.materiales.materiales.DTO.MaterialDTO;
import com.materiales.materiales.model.Material;
import com.materiales.materiales.repository.MaterialRepository;
import com.materiales.materiales.service.MaterialService;
import com.materiales.materiales.service.MaterialValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class MaterialesApplicationTests {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private MaterialValidaciones materialValidaciones;

    @InjectMocks
    private MaterialService materialService;

    private final Faker faker = new Faker();

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String nombreMaterialSimulado = faker.options().option("Lúpulo Cascade", "Malta Base", "Levadura Ale");
        Integer cantidadStockSimulado = faker.number().numberBetween(10, 1000);
        String proveedorSimulado = "Distribuidora de Insumos Santiago Ltda";

        Material materialFalso = new Material();
        materialFalso.setIdMaterial(idSimulado);
        materialFalso.setNombreMaterial(nombreMaterialSimulado);
        materialFalso.setCantidadStock(cantidadStockSimulado);
        materialFalso.setNombreProveedor(proveedorSimulado);

        MaterialDTO dtoFalso = new MaterialDTO();
        dtoFalso.setIdMaterial(idSimulado);
        dtoFalso.setNombreMaterial(nombreMaterialSimulado);
        dtoFalso.setCantidadStock(cantidadStockSimulado);
        dtoFalso.setNombreProveedor(proveedorSimulado);

        when(materialRepository.findById(idSimulado)).thenReturn(Optional.of(materialFalso));
        when(materialValidaciones.convertirADTO(materialFalso)).thenReturn(dtoFalso);

        MaterialDTO resultado = materialService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdMaterial());
        assertEquals(nombreMaterialSimulado, resultado.getNombreMaterial());
        assertEquals(cantidadStockSimulado, resultado.getCantidadStock());
        assertEquals(proveedorSimulado, resultado.getNombreProveedor());

        verify(materialRepository, times(1)).findById(idSimulado);
    }
}