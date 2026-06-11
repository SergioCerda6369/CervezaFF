package com.producciones.producciones.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.producciones.producciones.dto.ProduccionesDTO;
import com.producciones.producciones.model.Producciones;
import com.producciones.producciones.service.ProduccionesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producciones")
public class ProduccionesController {

    @Autowired
    private ProduccionesService produccionesService;

    @GetMapping
    public ResponseEntity<List<ProduccionesDTO>> todas() {
        List<ProduccionesDTO> lista = produccionesService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            ProduccionesDTO dto = produccionesService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Producciones produccion) {
        try {
            ProduccionesDTO dto = produccionesService.guardar(produccion);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la orden de producción", HttpStatus.BAD_REQUEST);
        }
    }
}
