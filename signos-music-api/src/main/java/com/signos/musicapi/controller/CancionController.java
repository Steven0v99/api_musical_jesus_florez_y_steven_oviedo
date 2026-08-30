package com.signos.musicapi.controller;

import com.signos.musicapi.dto.CancionDTO;
import com.signos.musicapi.model.Cancion;
import com.signos.musicapi.service.CancionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public ResponseEntity<List<Cancion>> obtenerTodas() {

        return ResponseEntity.ok(cancionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> obtenerPorId(@PathVariable Long id) {

        Cancion cancion = cancionService.obtenerPorId(id);

        if (cancion == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cancion);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>> buscarPorGenero(
            @RequestParam String genero) {

        List<Cancion> resultado =
                cancionService.buscarPorGenero(genero);

        return ResponseEntity.ok(resultado);
    }

    @PostMapping
    public ResponseEntity<Cancion> crearCancion(
            @RequestBody CancionDTO datos) {

        Cancion nuevaCancion =
                cancionService.crearCancion(datos);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaCancion);
    }
}