package com.signos.musicapi.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MusicDatabaseHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Aquí puedes simular o validar la conexión real a tu base de datos de música
        boolean conexionBDActiva = true; 

        if (!conexionBDActiva) {
            return Health.down()
                    .withDetail("error", "Sin conexión con la base de datos MySQL de canciones")
                    .build();
        }

        return Health.up()
                .withDetail("database", "MySQL")
                .withDetail("estado", "Operativo y conectado correctamente")
                .withDetail("modulo", "Signos Music API")
                .build();
    }
}