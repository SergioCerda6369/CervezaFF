package com.mantenimientos.mantenimientos.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mantenimientos.mantenimientos.dto.MantenimientosDTO;
import com.mantenimientos.mantenimientos.model.Mantenimientos;
import com.mantenimientos.mantenimientos.service.MantenimientosService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenimientos")
public class MantenimientosController {

    @Autowired
    private MantenimientosService mantenimientosService;

    @GetMapping
    public ResponseEntity<List<MantenimientosDTO>> todas() {
        List<MantenimientosDTO> lista = mantenimientosService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            MantenimientosDTO dto = mantenimientosService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Mantenimientos mantenimiento) {
        try {
            MantenimientosDTO dto = mantenimientosService.guardar(mantenimiento);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la orden de mantenimiento", HttpStatus.BAD_REQUEST);
        }
    }
}
