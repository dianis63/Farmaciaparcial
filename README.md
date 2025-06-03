# 💊 FSAN Rafael – Sistema de Gestión para Farmacia

Este proyecto es un backend para un sistema de gestión de farmacia hecho con **Spring Boot + Java + MySQL**. Permite administrar medicamentos, clientes, empleados y ventas. Se trata de una solución **escalable, segura y óptima** pensada para Farmacia San Rafael.

---

## 📌 Descripción del Proyecto

El sistema permite:

* CRUD de productos (medicamentos), empleados y clientes.
* Registro de ventas con edición de productos y cálculo automático del total.
* Gestión de ingresos de productos al inventario.
* Control de estado de ventas.
* Consulta de información por criterios como nombre, fecha o ID.
* Manejo de excepciones globales y respuestas personalizadas.
* Uso de DTOs y Mappers para desacoplar las entidades del modelo de presentación.

 
---

## 🛠 Tecnologías

* Java 17
* Spring Boot 3.3.10
* Spring Data JPA
* MySQL
* Maven
* Lombok

---

## 🧱 Estructura de Capas

* `model`: Entidades JPA.
* `repository`: Interfaces `JpaRepository`.
* `controller`: Endpoints REST organizados por entidad.
* `service`: Lógica intermedia entre controller y repository.
* `payload`: Clases auxiliares como `MessageResponse`, `GlobalExceptionHandler`.
* `mapper`: Conversión entre entidades y DTOs.
* `resources`: Configuración de base de datos (`application.properties`) y script de BD.

---

## 📂 Endpoints disponibles

Todos bajo el prefijo `/process`:

### 📦 Productos

* `GET /productos`: obtener todos los productos  
* `POST /producto`: crear un producto  
* `GET /ConsultaProducto`: buscar producto por ID  
* `GET /buscar?nombre=...`: buscar por nombre  
* `PUT /producto/{id}`: actualizar producto  
* `DELETE /producto/{id}`: eliminar producto  

### 🧍 Clientes

* `GET /clientes`: obtener todos los clientes  
* `POST /cliente`: registrar cliente  
* `GET /ConsultaCliente?id=...`: buscar cliente por ID  
* `PUT /cliente/{id}`: actualizar cliente  
* `DELETE /cliente/{id}`: eliminar cliente  

### 🧑‍⚕️ Empleados

* `GET /empleados`: obtener todos los empleados  
* `POST /empleado`: registrar empleado  
* `PUT /empleado/{id}`: actualizar empleado  
* `DELETE /empleado/{id}`: eliminar empleado  

### 🛒 Ventas

* `GET /ventas`: obtener todas las ventas  
* `POST /venta`: registrar nueva venta  
* `GET /venta/{id}`: buscar venta por ID  
* `GET /ConsultaVenta?id=...`: consulta avanzada  
* `GET /buscarv?fecha=YYYY-MM-DD`: buscar por fecha  
* `PUT /venta/{id}`: actualizar venta  
* `DELETE /venta/{id}`: eliminar venta  

### 🧾 Detalle de Venta

* `GET /detalle_ventas`: obtener todos los detalles  
* `POST /detalle_venta`: agregar detalle de venta  
* `GET /ConsultaDVenta?id=...`: obtener detalles por ID de venta  
* `GET /detalle_venta/{id}`: obtener detalle por ID específico  
* `PUT /detalle_venta/{id}`: actualizar detalle  
* `DELETE /detalle_venta/{id}`: eliminar detalle  

### 📥 Ingresos

* `GET /ingresos`: obtener ingresos de productos  
* `POST /ingreso`: registrar ingreso  
* `GET /ConsultaIngreso?cantidad=...`: ingresos menores a X unidades  
* `PUT /ingreso/{id}`: actualizar ingreso  
* `DELETE /ingreso/{id}`: eliminar ingreso  

---

## 🧠 Notas Técnicas

* Se usan DTOs para las peticiones y respuestas, desacoplando las entidades del modelo.
* `MessageResponse` se utiliza para dar respuestas uniformes con mensaje, datos y timestamp.
* `GlobalExceptionHandler` maneja errores como `IllegalArgumentException`, `NullPointerException`, errores de validación, entre otros.
* Relaciones entre entidades como Cliente, Empleado y Venta se manejan por ID en lugar de objetos anidados para mayor control.
 

---

## 📥 Instrucciones de instalación y uso

Sigue estos pasos para ejecutar el proyecto localmente en tu máquina con **IntelliJ IDEA** o cualquier otro IDE compatible con Java y Maven.

### 🔧 Requisitos previos

- **Java 17** instalado  
- **MySQL 8** o superior  
- **Maven** instalado o integrado en el IDE  
- **IntelliJ IDEA** (opcional, pero recomendado)

### 🚀 Pasos para ejecutar

1. **Clonar el repositorio**

  ```bash
  git clone https://github.com/tu_usuario/fsan-rafael-backend.git
  ```

2. **Crear la base de datos en MySQL**
Abre tu cliente de base de datos y ejecuta el script llamado `fsanrafael.sql` ubicado en la carpeta de Resources

3. **Configurar credenciales en  `application.properties`**
Ubica el archivo en src/main/resources/application.properties y asegúrate de completar tu usuario y contraseña de MySQL:
  ```bash
  spring.application.name=APIJava
  server.port=8080
  
  spring.datasource.url=jdbc:mysql://127.0.0.1:3306/fsanrafael
  spring.datasource.username=TU_USUARIO
  spring.datasource.password=TU_CONTRASEÑA
  
  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
  spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
  spring.jpa.show-sql=true
  ```

4. **Importar el proyecto en IntelliJ**

Abre IntelliJ → File > Open → Selecciona la carpeta del proyecto.
Confirma que se detecte como proyecto Maven.
Espera a que descargue las dependencias.


5. **Ejecutar el proyecto**

Desde IntelliJ:

Abre la clase APIJavaApplication.java.
Clic derecho → Run.


6. **Probar la API**
Una vez levantado el servidor, accede a la API usando la URL base:

  ```bash
  http://localhost:8080/process
  ```
Por ejemplo, para obtener todos los productos, puedes consultar todos los endpoints en este documento:

  ```bash
  GET http://localhost:8080/process/productos
  ```

---

## 👨‍💻 Autores
🖥️😡 CodeHaters Team – "Odiamos el código, pero lo escribimos igual."

- Diana Ortíz — Siempre sabe que algo anda mal, pero no sabe qué.

- Ashley Méndez  — Dice "debería ser fácil" justo antes del caos.

- Moisés Calderón — Testea todo... menos lo que falla.

- Javier Canesa  — Arregla cosas rompiendo otras más importantes.

- Javier Martínez  — Fixea bugs que no causó.
