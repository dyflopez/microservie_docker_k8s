package com.ms.email.msemial.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "msemail")
public class MsEmailConfig {


    public static final String BIENVENIDO = "bienvenida";

    public static final String RETIRO =  "retiro";

    public static final String ACTUALIZACION =  "actulizacion";
    private Map<String, String> mensaje;

    private Map<String, String> body;

    public String getTransactionType(final String typeCode) {
        return mensaje.get(typeCode);
    }

    public String getBodyMensaje(final String typeCode) {
        return body.get(typeCode);
    }

}
