package com.epet14.torneo.core;
import static com.epet14.torneo.core.Ejercicios.*;

import java.util.Locale;

public class Resolucion {

    public String getSolucion(String[] params) {

        // Region - TODO:  Parsear los argumentos al tipo que esperan en su función 



        // End


        var ans = 0; // <- TODO: Cambien 0 por la llamada a la función que armaron y pásenle los argumentos que parsearon.
        return parseAns(ans);
    }

    private String parseAns(Object ans) {
        if (ans instanceof String s) {
            return s;
        } else if (ans instanceof Double d) {
            if (d == Math.floor(d) && !d.isInfinite()) {
                return String.format(Locale.US, "%.0f", d);
            } else {
                return String.format(Locale.US, "%.2f", d);
            }
        } else {
            return ans.toString();
        }
    }

}
