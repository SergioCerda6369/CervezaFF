package com.calidades.calidades.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calidades.calidades.DTO.QualityDTO;
import com.calidades.calidades.service.QualityService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/calidades")
public class QualityController {

    @Autowired
    private QualityService qualityService;
    
    @GetMapping("/fermentation/{idFermentation}")
    public ResponseEntity<QualityDTO> buscarPorFermentation(@PathVariable Integer idFermentation) {
        QualityDTO dto = qualityService.obtenerPorId(idFermentation);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<QualityDTO> guardar(@Valid @RequestBody QualityDTO qualityDTO) {
        return new ResponseEntity<>(qualityService.guardar(qualityDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (qualityService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
