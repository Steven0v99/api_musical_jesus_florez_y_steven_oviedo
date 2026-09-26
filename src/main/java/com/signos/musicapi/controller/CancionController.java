package com.signos.musicapi.controller;

import com.signos.musicapi.dto.CancionDTO;
import com.signos.musicapi.model.Cancion;
import com.signos.musicapi.service.CancionService;
import com.signos.musicapi.service.ItunesExternalService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CancionService cancionService;
    private final ItunesExternalService itunesExternalService;

    // Constructor único que inyecta ambos servicios correctamente
    public CancionController(CancionService cancionService, ItunesExternalService itunesExternalService) {
        this.cancionService = cancionService;
        this.itunesExternalService = itunesExternalService;
    }

    // Endpoint GET /api/canciones (¡Este era el que tenías comentado!)
    @GetMapping
    public ResponseEntity<List<Cancion>> obtenerTodas() {
        return ResponseEntity.ok(cancionService.obtenerTodas());
    }

    // Endpoint GET /api/canciones/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Cancion> obtenerPorId(@PathVariable Long id) {
        Optional<Cancion> cancion = cancionService.obtenerPorId(id);
        return cancion.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint GET /api/canciones/buscar?genero=...
    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>> buscarPorGenero(@RequestParam String genero) {
        List<Cancion> resultado = cancionService.buscarPorGenero(genero);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint GET /api/canciones/externo/buscar?termino=... (API Externa - iTunes)
    @GetMapping("/externo/buscar")
    public ResponseEntity<String> buscarEnApiExterna(@RequestParam String termino) {
        String resultado = itunesExternalService.buscarMusicaExterna(termino);
        return ResponseEntity.ok(resultado);
    }

    // Endpoint POST /api/canciones
    @PostMapping
    public ResponseEntity<Cancion> crearCancion(@RequestBody CancionDTO datos) {
        Cancion nuevaCancion = cancionService.crearCancion(datos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCancion);
    }

    // Endpoint PUT /api/canciones/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Cancion> actualizarCancion(@PathVariable Long id, @RequestBody CancionDTO datos) {
        Cancion cancionActualizada = cancionService.actualizarCancion(id, datos);
        if (cancionActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cancionActualizada);
    }

    // Endpoint DELETE /api/canciones/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable Long id) {
        boolean eliminado = cancionService.eliminarCancion(id);
        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}