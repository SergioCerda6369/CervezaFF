package com.recetas.recetas.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.recetas.recetas.dto.RecetasDTO;
import com.recetas.recetas.model.Recetas;
import com.recetas.recetas.service.RecetasService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recetas")
public class RecetasController {

    @Autowired
    private RecetasService recetasService;

    @GetMapping
    public ResponseEntity<List<RecetasDTO>> todas() {
        List<RecetasDTO> lista = recetasService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable Integer id) {
        try {
            RecetasDTO dto = recetasService.buscarPorId(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Recetas receta) {
        try {
            RecetasDTO dto = recetasService.guardar(receta);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la receta de cerveza", HttpStatus.BAD_REQUEST);
        }
    }

}
