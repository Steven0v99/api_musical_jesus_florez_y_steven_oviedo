package com.signos.musicapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
public class ItunesExternalService {

    private static final Logger log = LoggerFactory.getLogger(ItunesExternalService.class);
    private final RestClient restClient;

    public ItunesExternalService() {
        // Inicializamos RestClient apuntando a la API pública de iTunes
        this.restClient = RestClient.create("https://itunes.apple.com");
    }

    public String buscarMusicaExterna(String termino) {
        try {
            log.info("Iniciando consulta a la API externa de iTunes para el término: {}", termino);
            
            String respuesta = restClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/search")
                            .queryParam("term", termino)
                            .queryParam("limit", 5)
                            .build())
                    .retrieve()
                    .body(String.class);

            log.info("Consulta externa completada exitosamente.");
            return respuesta;

        } catch (RestClientException e) {
            log.error("Error controlado al conectar con la API de iTunes: {}", e.getMessage());
            return "{\"error\": \"El servicio externo de música no se encuentra disponible temporalmente.\"}";
        }
    }
}