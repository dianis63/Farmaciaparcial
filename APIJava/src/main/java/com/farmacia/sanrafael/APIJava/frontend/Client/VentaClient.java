package com.farmacia.sanrafael.APIJava.frontend.Client;

import com.farmacia.sanrafael.APIJava.frontend.models.DetalleVentasDTO;
import com.farmacia.sanrafael.APIJava.frontend.models.MessageResponse;
import com.farmacia.sanrafael.APIJava.frontend.models.VentasDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class VentaClient {

    private static final String BASE_URL = "http://localhost:8080/process";
    private final ObjectMapper mapper = new ObjectMapper();

    // 1. Registrar nueva venta
    public VentasDTO registrarVenta(VentasDTO venta) throws Exception {
        URL url = new URL(BASE_URL + "/venta");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        mapper.writeValue(os, venta);
        os.flush();

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<VentasDTO>> typeRef = new TypeReference<>() {
        };
        MessageResponse<VentasDTO> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    // 2. Obtener todas las ventas
    public List<VentasDTO> obtenerVentas() throws Exception {
        URL url = new URL(BASE_URL + "/ventas");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<List<VentasDTO>>> typeRef = new TypeReference<>() {
        };
        MessageResponse<List<VentasDTO>> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    // 3. Buscar venta por ID
    public VentasDTO buscarPorId(Long id) throws Exception {
        URL url = new URL(BASE_URL + "/venta/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<VentasDTO>> typeRef = new TypeReference<>() {
        };
        MessageResponse<VentasDTO> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    // 4. Actualizar venta
    public VentasDTO actualizarVenta(Long id, VentasDTO venta) throws Exception {
        URL url = new URL(BASE_URL + "/venta/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        mapper.writeValue(os, venta);
        os.flush();

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<VentasDTO>> typeRef = new TypeReference<>() {
        };
        MessageResponse<VentasDTO> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    // 5. Eliminar venta
    public void eliminarVenta(Long id) throws Exception {
        URL url = new URL(BASE_URL + "/venta/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Error al eliminar la venta: " + responseCode);
        }
    }

    // 6. Buscar ventas por fecha
    public List<VentasDTO> buscarPorFecha(String fechaTexto) throws Exception {
        URL url = new URL(BASE_URL + "/buscarv?fecha=" + URLEncoder.encode(fechaTexto, StandardCharsets.UTF_8));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<List<VentasDTO>>> typeRef = new TypeReference<>() {
        };
        MessageResponse<List<VentasDTO>> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    public List<DetalleVentasDTO> obtenerDetalleVenta(Long idVenta) throws IOException {
        String url = BASE_URL + "/ConsultaDVenta?id_venta=" + idVenta;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            ObjectMapper mapper = new ObjectMapper();

            // Deserializar con el tipo parametrizado que espera List<DetalleVentasDTO> en "data"
            JavaType tipoRespuesta = mapper.getTypeFactory().constructParametricType(
                    MessageResponse.class,
                    mapper.getTypeFactory().constructCollectionType(List.class, DetalleVentasDTO.class)
            );

            MessageResponse<List<DetalleVentasDTO>> respuesta = mapper.readValue(connection.getInputStream(), tipoRespuesta);
            return respuesta.getData();
        } else {
            throw new IOException("Error HTTP: " + connection.getResponseCode());
        }
    }


}
