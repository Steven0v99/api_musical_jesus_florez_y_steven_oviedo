package com.signos.musicapi.service;

import com.signos.musicapi.dto.CancionDTO;
import com.signos.musicapi.model.Cancion;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CancionService {

    private final List<Cancion> canciones = new ArrayList<>();

    private Long siguienteId = 1L;

    public CancionService() {

        canciones.add(new Cancion(
                siguienteId++,
                "Si no te hubieras ido",
                "Marco Antonio Solís",
                "Balada",
                280
        ));

        canciones.add(new Cancion(
                siguienteId++,
                "Persiana Americana",
                "Soda Stereo",
                "Rock en español",
                285
        ));

        canciones.add(new Cancion(
                siguienteId++,
                "Lamento Boliviano",
                "Enanitos Verdes",
                "Rock en español",
                240
        ));

        canciones.add(new Cancion(
                siguienteId++,
                "Rayando el Sol",
                "Maná",
                "Rock en español",
                265
        ));
    }

    public List<Cancion> obtenerTodas() {
        return canciones;
    }

    public Cancion obtenerPorId(Long id) {

        for (Cancion cancion : canciones) {

            if (cancion.getId().equals(id)) {
                return cancion;
            }
        }

        return null;
    }

    public List<Cancion> buscarPorGenero(String genero) {

        List<Cancion> resultado = new ArrayList<>();

        for (Cancion cancion : canciones) {

            if (cancion.getGenero().equalsIgnoreCase(genero)) {
                resultado.add(cancion);
            }
        }

        return resultado;
    }

    public Cancion crearCancion(CancionDTO datos) {

        Cancion nuevaCancion = new Cancion(
                siguienteId++,
                datos.titulo(),
                datos.artista(),
                datos.genero(),
                datos.duracion()
        );

        canciones.add(nuevaCancion);

        return nuevaCancion;
    }
}