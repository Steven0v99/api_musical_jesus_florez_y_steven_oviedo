
package com.signos.musicapi.repository;

import com.signos.musicapi.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {

    // Busca canciones por el nombre del género,
    // ignorando mayúsculas y minúsculas
    List<Cancion> findByGenero_NombreIgnoreCase(String genero);
}

