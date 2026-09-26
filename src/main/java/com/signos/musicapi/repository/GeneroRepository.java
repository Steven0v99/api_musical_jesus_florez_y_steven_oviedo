
package com.signos.musicapi.repository;

import com.signos.musicapi.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long> {

    Optional<Genero> findByNombreIgnoreCase(String nombre);
}
