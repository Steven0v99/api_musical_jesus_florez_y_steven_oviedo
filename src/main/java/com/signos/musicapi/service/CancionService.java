package com.signos.musicapi.service;

import com.signos.musicapi.dto.CancionDTO;
import com.signos.musicapi.model.Cancion;
import com.signos.musicapi.model.Genero;
import com.signos.musicapi.repository.CancionRepository;
import com.signos.musicapi.repository.GeneroRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    // Logger
    private static final Logger log =
            LoggerFactory.getLogger(CancionService.class);

    private final CancionRepository cancionRepository;
    private final GeneroRepository generoRepository;

    // Métrica personalizada
    private final Counter cancionesCreadasCounter;

    // Constructor
    public CancionService(
            CancionRepository cancionRepository,
            GeneroRepository generoRepository,
            MeterRegistry registry) {

        this.cancionRepository = cancionRepository;
        this.generoRepository = generoRepository;

        // Métrica personalizada
        this.cancionesCreadasCounter =
                registry.counter("app.canciones.creadas.total");
    }

    // Obtener todas las canciones
    public List<Cancion> obtenerTodas() {

        log.info("Ejecutando consulta: Obteniendo todas las canciones registradas.");

        return cancionRepository.findAll();
    }

    // Obtener canción por ID
    public Optional<Cancion> obtenerPorId(Long id) {

        log.info("Buscando canción por ID: {}", id);

        return cancionRepository.findById(id);
    }

    // Buscar canciones por género
    public List<Cancion> buscarPorGenero(String genero) {

        log.info("Buscando canciones del género: {}", genero);

        return cancionRepository.findByGenero_NombreIgnoreCase(genero);
    }

    // Crear canción
    public Cancion crearCancion(CancionDTO datos) {

        log.warn(
                "Creando nueva canción en el sistema con título: {}",
                datos.titulo()
        );

        // Buscamos el género por su nombre
        Genero genero = generoRepository
                .findByNombreIgnoreCase(datos.genero())
                .orElseThrow(() ->
                        new RuntimeException("El género no existe")
                );

        // Creamos la canción utilizando el objeto Genero
        Cancion nuevaCancion = new Cancion(
                datos.titulo(),
                datos.artista(),
                genero,
                datos.duracion()
        );

        // Guardamos la canción
        Cancion guardada = cancionRepository.save(nuevaCancion);

        // Incrementamos la métrica
        cancionesCreadasCounter.increment();

        log.info(
                "Canción creada exitosamente con ID: {}",
                guardada.getId()
        );

        return guardada;
    }

    // Actualizar canción
    public Cancion actualizarCancion(Long id, CancionDTO datos) {

        return cancionRepository.findById(id).map(cancion -> {

            log.info(
                    "Actualizando información de la canción con ID: {}",
                    id
            );

            // Buscamos el nuevo género
            Genero genero = generoRepository
                    .findByNombreIgnoreCase(datos.genero())
                    .orElseThrow(() ->
                            new RuntimeException("El género no existe")
                    );

            cancion.setTitulo(datos.titulo());
            cancion.setArtista(datos.artista());
            cancion.setGenero(genero);
            cancion.setDuracion(datos.duracion());

            return cancionRepository.save(cancion);

        }).orElseGet(() -> {

            log.error(
                    "Fallo al actualizar: No se encontró la canción con ID: {}",
                    id
            );

            return null;
        });
    }

    // Eliminar canción
    public boolean eliminarCancion(Long id) {

        if (cancionRepository.existsById(id)) {

            log.warn(
                    "Eliminando canción con ID: {}",
                    id
            );

            cancionRepository.deleteById(id);

            return true;
        }

        log.error(
                "Intento fallido de eliminación: La canción con ID {} no existe.",
                id
        );

        return false;
    }
}

