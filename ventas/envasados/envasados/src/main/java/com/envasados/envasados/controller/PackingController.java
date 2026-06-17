package com.envasados.envasados.controller;

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

import com.envasados.envasados.DTO.PackingDTO;
import com.envasados.envasados.model.Packing;
import com.envasados.envasados.service.PackingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/envasados")
public class PackingController {

    @Autowired
    private PackingService packingService;

    @GetMapping
    public ResponseEntity<List<PackingDTO>> listar() {
        return new ResponseEntity<>(packingService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PackingDTO> guardar(@Valid @RequestBody Packing packing) {
        return new ResponseEntity<>(packingService.guardar(packing), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (packingService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
