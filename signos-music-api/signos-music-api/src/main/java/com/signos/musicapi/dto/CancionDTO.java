package com.signos.musicapi.dto;

public record CancionDTO(
        String titulo,
        String artista,
        String genero,
        Integer duracion
) {
}