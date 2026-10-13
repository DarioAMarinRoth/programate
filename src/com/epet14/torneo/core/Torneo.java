package com.epet14.torneo.core;

import com.epet14.torneo.net.Conexion;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Torneo {

    private final String host = "hornero2.fi.uncoma.edu.ar";
    private final String tokenTorneo;
    private final String numeroProblema;
    private String tokenRespuesta;
    private final Conexion conexionHTTP;
    private final static Logger LOGGER = LoggerFactory.getLogger("Server");

    public Torneo(String tokenTorneo, String numeroProblema) {
        this.tokenTorneo = tokenTorneo;
        this.numeroProblema = numeroProblema;
        this.conexionHTTP = new Conexion();
    }

    public String[] recuperarParametrosProblema() {
        String url = String.format("http://%s/api/jugar/solicitud?token=%s&problema=%s", this.host, this.tokenTorneo, this.numeroProblema);
        LOGGER.trace("URL Solicitud:\n{}", url);
        String[] rs = null;
        try {
            String respuesta = this.conexionHTTP.sendGet(url);
            JSONObject jsonObject = new JSONObject(respuesta);
            String parametros = jsonObject.getString("parametrosEntrada");
            this.tokenRespuesta = jsonObject.getString("token");
            rs = parametros.split(",");
        } catch (Exception e) {
            LOGGER.error("Error al recuperar los parámetros del problema", e);
        }
        return rs;
    }

    public String recuperarRespuestaImplementacion(String laSolucion) {
        String url = String.format("http://%s/api/jugar/respuesta?tokenSolicitud=%s&solucion=%s", this.host, this.tokenRespuesta, laSolucion);
        String respuesta;
        String mensajeRta = "";
        try {
            LOGGER.trace(url);
            respuesta = this.conexionHTTP.sendGet(url);
            LOGGER.trace("URL Respuesta:\n{}", url);
            LOGGER.trace("Respuesta obtenida: {}.", respuesta);
            JSONObject jsonObject = new JSONObject(respuesta);
            mensajeRta = jsonObject.getString("estado");
        } catch (Exception e) {
            LOGGER.error("Error al recuperar la implementación de la respuesta", e);
        }
        return (mensajeRta);
    }
}
