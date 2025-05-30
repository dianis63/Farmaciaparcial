package com.farmacia.sanrafael.APIJava.frontend.Client;

import com.farmacia.sanrafael.APIJava.frontend.models.MessageResponse;
import com.farmacia.sanrafael.APIJava.frontend.models.ProductoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ProductoClient {

    private static final String BASE_URL = "http://localhost:8080/process";
    private final ObjectMapper mapper = new ObjectMapper();

    // 1. Obtener todos los productos
    public List<ProductoDTO> obtenerProductos() throws Exception {
        URL url = new URL(BASE_URL + "/productos");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<List<ProductoDTO>>> typeRef =
                new TypeReference<>() {
                };
        MessageResponse<List<ProductoDTO>> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    // 2. Buscar productos por nombre
    public List<ProductoDTO> buscarPorNombre(String nombre) throws Exception {
        URL url = new URL(BASE_URL + "/buscar?nombre=" + nombre);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<List<ProductoDTO>>> typeRef =
                new TypeReference<>() {
                };
        MessageResponse<List<ProductoDTO>> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    // 3. Guardar nuevo producto (POST)
    public ProductoDTO guardarProducto(ProductoDTO producto) throws Exception {
        URL url = new URL(BASE_URL + "/producto");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        mapper.writeValue(os, producto);
        os.flush();

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<ProductoDTO>> typeRef = new TypeReference<>() {
        };
        MessageResponse<ProductoDTO> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }

    // 4. Buscar por ID (GET)
    public List<ProductoDTO> buscarPorId(long id) throws Exception {
        URL url = new URL(BASE_URL + "/ConsultaProducto?idProducto=" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<List<ProductoDTO>>> typeRef =
                new TypeReference<>() {
                };
        MessageResponse<List<ProductoDTO>> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }
    // 5. Eliminar producto (DELETE)
    public void eliminarProducto(long id) throws Exception {
        URL url = new URL(BASE_URL + "/producto/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error al eliminar el producto: " + responseCode);
        }
    }

    // 6. Actualizar producto (PUT)
    public ProductoDTO actualizarProducto(long id, ProductoDTO producto) throws Exception {
        URL url = new URL(BASE_URL + "/producto/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        mapper.writeValue(os, producto);
        os.flush();

        InputStream inputStream = conn.getInputStream();
        TypeReference<MessageResponse<ProductoDTO>> typeRef = new TypeReference<>() {
        };
        MessageResponse<ProductoDTO> response = mapper.readValue(inputStream, typeRef);

        return response.getData();
    }
}
