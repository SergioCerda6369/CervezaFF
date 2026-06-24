package com.fermentaciones.fermentaciones.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.model.Fermentation;
import com.fermentaciones.fermentaciones.repository.FermentationRepository;
import com.fermentaciones.fermentaciones.service.FermentacionesService;

import net.datafaker.Faker;


@ExtendWith(MockitoExtension.class)
class FermentacionesApplicationTests {

    @Mock
    private FermentationRepository fermentationRepository;

    @InjectMocks
    private FermentacionesService fermentationService;
    private Faker faker = new Faker();
     @BeforeEach
     void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        String codigoTanqueSimulado = "TK-" + faker.number().numberBetween(100, 999);;
        Integer temperaturaSimulado = faker.number().numberBetween(30, 150);

        Fermentation ferFalsa = new Fermentation();
        ferFalsa.setIdFermentation(idSimulado); 
        ferFalsa.setCodigoTanque(codigoTanqueSimulado);
        ferFalsa.setTemperaturaActual(temperaturaSimulado);

        when(fermentationRepository.findById(idSimulado)).thenReturn(Optional.of(ferFalsa));

        FermentationDTO resultado = fermentationService.buscarPorId(idSimulado);

        assertNotNull(resultado, "El DTO de la fermentación no debería ser nulo");
        assertEquals(idSimulado, resultado.getIdFermentation(), "El ID debe coincidir");
        assertEquals(codigoTanqueSimulado, resultado.getCodigoTanque(), "El código del tanque debe coincidir");
        assertEquals(temperaturaSimulado, resultado.getTemperaturaActual(), "La temperatura debe coincidir");

        verify(fermentationRepository, times(1)).findById(idSimulado);

    }

}
