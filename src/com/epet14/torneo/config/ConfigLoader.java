package com.epet14.torneo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties campos = new Properties();

    static {
        try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream("torneo.properties")) {
            if (in == null) {
                throw new RuntimeException("No se encontró torneo.properties en el classpath");
            }
            campos.load(in);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la configuración", e);
        }
    }

    private static String getValidProperty(String key) {
        String value = campos.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException(String.format("Propiedad faltante o vacía: '%s'", key));
        }

        if (value.trim().startsWith("${") && value.trim().endsWith("}")) {
            throw new IllegalStateException(String.format("Variable no resuelta para '%s'. Valor actual: '%s'", key, value));
        }

        return value;
    }

    public static String getToken() {
        return getValidProperty("token.torneo");
    }

    public static String getNumeroProblema() {
        return getValidProperty("numero.problema");
    }
}