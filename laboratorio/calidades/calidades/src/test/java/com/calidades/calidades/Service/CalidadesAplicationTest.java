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
import com.calidades.calidades.service.QualityValidaciones;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class CalidadesAplicationTest {

    @Mock
    private QualityRepository qualityRepository;

    @Mock
    private QualityValidaciones qualityValidaciones;

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

        QualityDTO dtoFalso = new QualityDTO();
        dtoFalso.setIdQuality(idSimulado);
        dtoFalso.setCantidadPh(phAleatorio);
        dtoFalso.setControlQuality(controlAleatorio);

        when(qualityRepository.findById(idSimulado)).thenReturn(Optional.of(calidadFalsa));
        when(qualityValidaciones.convertirADTO(calidadFalsa)).thenReturn(dtoFalso);

        QualityDTO resultado = qualityService.obtenerPorId(idSimulado);

        assertNotNull(resultado, "El DTO resultante no debería ser nulo");
        assertEquals(phAleatorio, resultado.getCantidadPh(), "La cantidad de PH debe coincidir con la de la DB");
        assertEquals(controlAleatorio, resultado.isControlQuality(), "El estado de control de calidad debe coincidir con el de la DB");

        verify(qualityRepository, times(1)).findById(idSimulado);
    }
        

}
