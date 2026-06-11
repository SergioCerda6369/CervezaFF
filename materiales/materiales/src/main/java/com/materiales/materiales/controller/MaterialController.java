package com.materiales.materiales.controller;

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

import com.materiales.materiales.DTO.MaterialDTO;
import com.materiales.materiales.model.Material;
import com.materiales.materiales.service.MaterialService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> listar() {
        return new ResponseEntity<>(materialService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MaterialDTO> guardar(@Valid @RequestBody Material material) {
        return new ResponseEntity<>(materialService.guardar(material), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (materialService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
