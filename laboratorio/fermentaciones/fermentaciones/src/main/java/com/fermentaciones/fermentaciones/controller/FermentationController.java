package com.fermentaciones.fermentaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fermentaciones.fermentaciones.DTO.FermentationDTO;
import com.fermentaciones.fermentaciones.model.Fermentation;
import com.fermentaciones.fermentaciones.service.FermentacionesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/fermentaciones")
public class FermentationController {

    @Autowired
    private FermentacionesService fermentationService;

    @GetMapping
    public ResponseEntity<List<FermentationDTO>> listar() {
        return new ResponseEntity<>(fermentationService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FermentationDTO> guardar(@Valid @RequestBody Fermentation fermentacion) {
        return new ResponseEntity<>(fermentationService.guardar(fermentacion), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (fermentationService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}