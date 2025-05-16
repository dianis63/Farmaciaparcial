# 💊 FSAN Rafael – Sistema de Gestión para Farmacia

Este proyecto es un backend para un sistema de gestión de farmacia hecho con **Spring Boot + Java + MySQL**. Permite administrar medicamentos, clientes, empleados y ventas. Se trata de una solución **escalable, segura y óptima** pensada para Farmacia San Rafael.

---

## 📌 Descripción del Proyecto

El sistema permite:

* CRUD de productos (medicamentos), empleados y clientes. (⚠️ Trabjando en...)
* Registro de ventas con edición de productos y cálculo automático del total.
* Gestión de ingresos de productos al inventario.
* Generación futura de reportes de ventas diarios, mensuales y por producto.
* Control de estado de ventas.

> El frontend aún no está definido, pero se integrará con otro framework moderno.

---

## 🛠 Tecnologías

* Java 17
* Spring Boot 3
* Spring Data JPA
* MySQL
* Lombok
* Maven
* (Futuro: Framework frontend moderno)

---

## 🧱 Estructura de Capas

* `model`: Entidades JPA.
* `repository`: Interfaces `JpaRepository`.
* `controller`: Endpoints REST organizados por entidad.
* `service`: Lógica intermedia entre controller y repository.
* `resources`: Configuración de conexión a base de datos.

---

## 📂 Endpoints disponibles (⚠️ Trabjando en, put, patch, delete...)

Todos bajo el prefijo `/process`:

### 📦 Productos

* `GET /productos`: obtener todos los productos
* `POST /producto`: crear un producto

### 🧍 Clientes

* `GET /clientes`: obtener todos los clientes
* `POST /cliente`: registrar cliente

### 🧑‍⚕️ Empleados

* `GET /empleados`: obtener todos los empleados
* `POST /empleado`: registrar empleado

### 🛒 Ventas

* `GET /ventas`: obtener todas las ventas
* `POST /venta`: registrar venta con estado e identificación única

### 🧾 Detalle de Venta

* `GET /detalle_ventas`: obtener todos los detalles de venta
* `POST /detalle_venta`: registrar detalle asociado a una venta

### 📥 Ingresos

* `GET /ingresos`: obtener ingresos de productos
* `POST /ingreso`: registrar ingreso

---

## 🧠 Notas Técnicas

* Se usa anotación `@Column(name = "nombre_real")` para evitar errores al mapear columnas en las entitites.
* Relaciones entre entidades se manejan mediante IDs en lugar de objetos directos para mayor control.
* Aún no se implementan métodos `PUT`, `PATCH` o `DELETE`.
* Ya están funcionales los métodos `GET` y `POST` de prueba.
* Ideal para integrarse fácilmente con cualquier cliente frontend (web o móvil).

