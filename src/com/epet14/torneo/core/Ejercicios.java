package com.epet14.torneo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


public class Ejercicios {

    private static final Logger LOGGER = LoggerFactory.getLogger("Tests");

    static void main() {
        Resolucion tester = new Resolucion();
        String[] params = {"", "", ""};  // * <- editen los elementos de este vector por los valores de argumentos que quieran usar para probar.

        LOGGER.info("Test realizado con los argumentos {}.", Arrays.toString(params));
        LOGGER.info("Respuesta obtenida: {}.", tester.getSolucion(params));
    }


    // Region -  Implementen sus funciones acá 






    // End
}
