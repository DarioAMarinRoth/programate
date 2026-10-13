package com.epet14.torneo;

import com.epet14.torneo.config.ConfigLoader;
import com.epet14.torneo.core.Resolucion;
import com.epet14.torneo.core.Torneo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Arrays;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger("Main");

    static void main() {
        Resolucion laResolucion = new Resolucion();
        String token;
        String problema;

        try {
            token = ConfigLoader.getToken();
            problema = ConfigLoader.getNumeroProblema();

            int respuesta = JOptionPane.showConfirmDialog(
                    null,
                    "El ejercicio seleccionado es " + problema + ". ¿Están seguros que cambiaron el llamado a la función en la clase Resolución? Ante la duda, vayan a confirmar." ,
                    "Check",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (respuesta != JOptionPane.OK_OPTION) {
                return;
            }

        } catch (IllegalStateException e) {
            LOGGER.error("Error de configuración: {} - Revise (torneo.properties:1)", e.getMessage(), e);
            return;
        } catch (RuntimeException e) {
            LOGGER.error("Fallo general al acceder a la configuración", e);
            return;
        }

        Torneo elTorneo = new Torneo(token, problema);

        String[] parametros = elTorneo.recuperarParametrosProblema();
        LOGGER.info("Se probará la implementación con los argumentos: {}", Arrays.toString(parametros));

        String respuesta = laResolucion.getSolucion(parametros);
        LOGGER.info("La salida de su implementación fue: {}", respuesta);

        String rta = elTorneo.recuperarRespuestaImplementacion(respuesta);
        LOGGER.info("Resultado de la validación: {}", rta);
    }
}
