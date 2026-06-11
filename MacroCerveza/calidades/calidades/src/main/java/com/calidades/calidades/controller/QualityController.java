package com.calidades.calidades.controller;

import java.util.List;

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
import com.calidades.calidades.model.Quality;
import com.calidades.calidades.service.QualityService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/calidades")

public class QualityController {
    @Autowired
    private QualityService qualityService;
    
    
    @GetMapping
    public ResponseEntity<List<QualityDTO>> listar() {
        return new ResponseEntity<>(qualityService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<QualityDTO> guardar(@Valid @RequestBody Quality quality) {
        return new ResponseEntity<>(qualityService.guardar(quality), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (qualityService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
