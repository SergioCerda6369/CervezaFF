package com.recetas.recetas.Service;

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

import com.recetas.recetas.dto.RecetasDTO;
import com.recetas.recetas.model.Recetas;
import com.recetas.recetas.repository.RecetasRepository;
import com.recetas.recetas.service.RecetasService;
import com.recetas.recetas.service.RecetasValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class RecetasApplicationTests {

    @Mock
    private RecetasRepository recetasRepository;

    @Mock
    private RecetasValidaciones recetasValidaciones;

    @InjectMocks
    private RecetasService recetasService;

    private final Faker faker = new Faker();

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String nombreCervezaSimulado = faker.options().option("Irish Stout", "Amber Ale", "Session IPA");
        String descripcionSimulada = faker.lorem().sentence();
        String ingredientesSimulados = "Malta Caramelo, Lúpulo Centennial";
        Integer tiempoCoccionSimulado = faker.number().numberBetween(60, 120);
        Integer diasFermentacionSimulado = faker.number().numberBetween(7, 21);

        Recetas recetaFalsa = new Recetas();
        recetaFalsa.setIdReceta(idSimulado);
        recetaFalsa.setNombreCerveza(nombreCervezaSimulado);
        recetaFalsa.setDescripcion(descripcionSimulada);
        recetaFalsa.setIngredientes(ingredientesSimulados);
        recetaFalsa.setTiempoCoccionMinutos(tiempoCoccionSimulado);
        recetaFalsa.setDiasFermentacion(diasFermentacionSimulado);

        RecetasDTO dtoFalso = new RecetasDTO();
        dtoFalso.setIdReceta(idSimulado);
        dtoFalso.setNombreCerveza(nombreCervezaSimulado);
        dtoFalso.setDescripcion(descripcionSimulada);
        dtoFalso.setIngredientes(ingredientesSimulados);
        dtoFalso.setTiempoCoccionMinutos(tiempoCoccionSimulado);
        dtoFalso.setDiasFermentacion(diasFermentacionSimulado);

        when(recetasRepository.findById(idSimulado)).thenReturn(Optional.of(recetaFalsa));
        when(recetasValidaciones.convertirADto(recetaFalsa)).thenReturn(dtoFalso);

        RecetasDTO resultado = recetasService.buscarPorId(idSimulado);

        assertNotNull(resultado);
        assertEquals(idSimulado, resultado.getIdReceta());
        assertEquals(nombreCervezaSimulado, resultado.getNombreCerveza());
        assertEquals(descripcionSimulada, resultado.getDescripcion());
        assertEquals(ingredientesSimulados, resultado.getIngredientes());
        assertEquals(tiempoCoccionSimulado, resultado.getTiempoCoccionMinutos());
        assertEquals(diasFermentacionSimulado, resultado.getDiasFermentacion());

        verify(recetasRepository, times(1)).findById(idSimulado);
    }
}