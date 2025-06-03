CREATE DATABASE fsanrafael;
USE fsanrafael;

-- Tabla de Productos
CREATE TABLE Producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(200),
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    fecha_vencimiento DATE
);

-- Tabla de Clientes
CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    correo VARCHAR(25)
);

-- Tabla de Empleados
CREATE TABLE Empleado (	
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    correo VARCHAR(25),
    telefono VARCHAR(20)
);

-- Tabla de Ventas
CREATE TABLE Venta (
    id_venta INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
    total DOUBLE NOT NULL,
    id_cliente INT NOT NULL,
    id_empleado INT NOT NULL,
    estado VARCHAR(1) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);

-- Tabla de DetalleVenta
CREATE TABLE Detalleventa (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES Venta(id_venta),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);


-- Tabla de Ingresos (Registro de entrada de productos)
CREATE TABLE Ingreso (
    id_ingreso INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio_compra DOUBLE NOT NULL,
    fecha_ingreso TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    id_empleado INT NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id_empleado)
);


