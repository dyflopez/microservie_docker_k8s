package com.spring.ms.usuario.demo.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "msemail")
public class MsEmailConfig {

    private Map<String, String> mensaje;

    public static final String BIENVENIDO = "bienvenida";

    public static final String RETIRO =  "retiro";

    public static final String ACTUALIZACION =  "actulizacion";


    public String getTransactionType(final String typeCode) {
        return mensaje.get(typeCode);
    }

}
