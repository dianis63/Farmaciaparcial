package com.farmacia.sanrafael.APIJava.frontend.Client;

import com.farmacia.sanrafael.APIJava.frontend.models.DetalleVentasDTO;
import com.farmacia.sanrafael.APIJava.frontend.models.MessageResponse;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DetalleVentaClient {

    private static final String BASE_URL = "http://localhost:8080/process";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<DetalleVentasDTO> obtenerDetalleVentas() throws IOException {
        URL url = new URL(BASE_URL + "/detalle_ventas");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        JavaType tipoRespuesta = mapper.getTypeFactory().constructParametricType(
                MessageResponse.class,
                mapper.getTypeFactory().constructCollectionType(List.class, DetalleVentasDTO.class)
        );

        MessageResponse<List<DetalleVentasDTO>> respuesta = mapper.readValue(conn.getInputStream(), tipoRespuesta);
        return respuesta.getData();
    }

    public DetalleVentasDTO registrarDetalleVenta(DetalleVentasDTO detalle) throws IOException {
        URL url = new URL(BASE_URL + "/detalle_venta");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        mapper.writeValue(os, detalle);
        os.flush();

        JavaType tipoRespuesta = mapper.getTypeFactory().constructParametricType(
                MessageResponse.class,
                DetalleVentasDTO.class
        );

        MessageResponse<DetalleVentasDTO> respuesta = mapper.readValue(conn.getInputStream(), tipoRespuesta);
        return respuesta.getData();
    }

    public void eliminarDetalleVenta(Long id) throws IOException {
        URL url = new URL(BASE_URL + "/detalle_venta/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error al eliminar detalle venta: " + conn.getResponseCode());
        }
    }

    public DetalleVentasDTO actualizarDetalleVenta(Long id, DetalleVentasDTO detalle) throws IOException {
        URL url = new URL(BASE_URL + "/detalle_venta/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        mapper.writeValue(os, detalle);
        os.flush();

        JavaType tipoRespuesta = mapper.getTypeFactory().constructParametricType(
                MessageResponse.class,
                DetalleVentasDTO.class
        );

        MessageResponse<DetalleVentasDTO> respuesta = mapper.readValue(conn.getInputStream(), tipoRespuesta);
        return respuesta.getData();
    }
}
