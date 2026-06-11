package com.despachos.despachos.controller;

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

import com.despachos.despachos.DTO.DispatchDTO;
import com.despachos.despachos.model.Dispatch;
import com.despachos.despachos.service.DispatchService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/despachos")
public class DispatchController {
    @Autowired
    private DispatchService dispatchService;

    @GetMapping
    public ResponseEntity<List<DispatchDTO>> listar() {
        return new ResponseEntity<>(dispatchService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DispatchDTO> guardar(@Valid @RequestBody Dispatch dispatch) {
        return new ResponseEntity<>(dispatchService.guardar(dispatch), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (dispatchService.eliminar(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
