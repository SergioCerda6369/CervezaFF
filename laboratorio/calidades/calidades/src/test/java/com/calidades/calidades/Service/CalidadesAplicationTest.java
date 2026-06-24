package com.calidades.calidades.Service;

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

import com.calidades.calidades.DTO.QualityDTO;
import com.calidades.calidades.model.Quality;
import com.calidades.calidades.repository.QualityRepository;
import com.calidades.calidades.service.QualityService;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class CalidadesAplicationTest {

    @Mock
    private QualityRepository qualityRepository;

    @InjectMocks
    private QualityService qualityService;
    private Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarPorIdExitoso() {
        Integer idSimulado = faker.number().numberBetween(1, 100);
        Integer phAleatorio = faker.number().numberBetween(3, 5);
        boolean controlAleatorio = faker.bool().bool();

        Quality calidadFalsa = new Quality();
        calidadFalsa.setIdQuality(idSimulado);
        calidadFalsa.setCantidadPh(phAleatorio);
        calidadFalsa.setControlQuality(controlAleatorio);
        
        when(qualityRepository.findById(idSimulado)).thenReturn(Optional.of(calidadFalsa));

        QualityDTO resultado = qualityService.obtenerPorId(idSimulado);

        assertNotNull(resultado, "El DTO resultante no debería ser nulo");
		assertEquals(phAleatorio, controlAleatorio, "El nombre transformado al DTO debe coincidir con el de la DB");

        verify(qualityRepository, times(1)).findById(idSimulado);

    }

}
