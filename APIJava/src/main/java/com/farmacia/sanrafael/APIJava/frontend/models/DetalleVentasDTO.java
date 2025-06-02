package com.farmacia.sanrafael.APIJava.frontend.models;

public class DetalleVentasDTO {
    private long id_detalle;
    private long id_venta;
    private long id_producto;
    private int cantidad;
    private double precio_unitario;
    private String nombre_producto;

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public long getid_detalle() {
        return id_detalle;
    }

    public void setid_detalle(long id_detalle) {
        this.id_detalle = id_detalle;
    }

    public long getid_venta() {
        return id_venta;
    }

    public void setid_venta(long id_venta) {
        this.id_venta = id_venta;
    }

    public long getid_producto() {
        return id_producto;
    }

    public void setid_producto(long id_producto) {
        this.id_producto = id_producto;
    }

    public int getcantidad() {
        return cantidad;
    }

    public void setcantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getprecio_unitario() {
        return precio_unitario;
    }

    public void setprecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
}