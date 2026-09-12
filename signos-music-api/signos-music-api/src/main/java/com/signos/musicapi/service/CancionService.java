package com.signos.musicapi.service;

import com.signos.musicapi.dto.CancionDTO;
import com.signos.musicapi.model.Cancion;
import com.signos.musicapi.repository.CancionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    private final CancionRepository cancionRepository;

    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    public List<Cancion> obtenerTodas() {
        return cancionRepository.findAll();
    }

    public Optional<Cancion> obtenerPorId(Long id) {
        return cancionRepository.findById(id);
    }

    public List<Cancion> buscarPorGenero(String genero) {
        return cancionRepository.findByGeneroIgnoreCase(genero);
    }

    public Cancion crearCancion(CancionDTO datos) {
        Cancion nuevaCancion = new Cancion(
                datos.titulo(),
                datos.artista(),
                datos.genero(),
                datos.duracion()
        );
        return cancionRepository.save(nuevaCancion);
    }

    public Cancion actualizarCancion(Long id, CancionDTO datos) {
        return cancionRepository.findById(id).map(cancion -> {
            cancion.setTitulo(datos.titulo());
            cancion.setArtista(datos.artista());
            cancion.setGenero(datos.genero());
            cancion.setDuracion(datos.duracion());
            return cancionRepository.save(cancion);
        }).orElse(null);
    }

    public boolean eliminarCancion(Long id) {
        if (cancionRepository.existsById(id)) {
            cancionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}