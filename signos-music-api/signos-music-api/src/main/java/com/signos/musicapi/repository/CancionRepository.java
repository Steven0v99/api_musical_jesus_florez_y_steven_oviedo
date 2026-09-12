package com.signos.musicapi.repository;

import com.signos.musicapi.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {

    // Consulta personalizada para buscar canciones por género ignorando mayúsculas/minúsculas
    List<Cancion> findByGeneroIgnoreCase(String genero);
}