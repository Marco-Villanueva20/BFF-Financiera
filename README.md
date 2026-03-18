# Proyecto Reto Técnico - Microservicios Financieros

Este proyecto consiste en una arquitectura de microservicios diseñada para gestionar clientes y sus productos financieros (cuentas de ahorro y tarjetas de crédito). Utiliza un enfoque reactivo y moderno con Spring Boot 3 y Spring WebFlux.

## Arquitectura del Sistema

El sistema está compuesto por los siguientes módulos:

1.  **`server-oauth` (Puerto 9000):** Servidor de autorización basado en Spring Authorization Server. Gestiona la autenticación y emisión de tokens JWT. Utiliza una base de datos PostgreSQL llamada `auth`.
2.  **`ms-cliente` (Puerto 8082):** Microservicio encargado de la gestión de la información de los clientes. Utiliza programación reactiva con WebFlux y R2DBC para interactuar con la base de datos `reto-tecnico`.
3.  **`ms-productos-financieros` (Puerto 8083):** Microservicio que gestiona las cuentas de ahorro y tarjetas de crédito de los clientes. También es reactivo y utiliza R2DBC.
4.  **`ms-bff` (Puerto 8081):** *Backend For Frontend*. Actúa como un agregador que consume datos de `ms-cliente` y `ms-productos-financieros` para ofrecer una vista consolidada al cliente (Resumen de productos).
5.  **`common-exception`:** Librería compartida para el manejo estandarizado de excepciones en los microservicios.
6.  **`init-db`:** Contiene los scripts SQL de inicialización para las bases de datos PostgreSQL.

### Diagrama de Flujo Lógico

```text
[Cliente/Frontend]
      |
      |-- (1) Autenticación --> [server-oauth] (JWT)
      |
      |-- (2) GET /api/v1/resumen --> [ms-bff]
                                        |
                                        |-- (3) GET /api/v1/clientes/usuario/{id} --> [ms-cliente]
                                        |
                                        |-- (4) GET /api/v1/productos/cliente/{id} --> [ms-productos-financieros]
```

## Tecnologías Utilizadas

-   **Java 17**
-   **Spring Boot 3.5.10 / 4.0.1**
-   **Spring WebFlux** (Programación Reactiva)
-   **Spring Security & OAuth2** (Resource Server & Authorization Server)
-   **R2DBC** (Acceso a datos reactivo)
-   **PostgreSQL**
-   **Docker & Docker Compose**
-   **Maven**
-   **Lombok**
-   **MapStruct**
-   **Zipkin / Micrometer Tracing** (Trazabilidad distribuida)
-   **OpenAPI / Swagger**

## Requisitos Previos

-   Docker y Docker Compose instalados.
-   Java 17 instalado.
-   Maven instalado (o usar el `mvnw` incluido).

## Configuración y Ejecución

### 1. Levantar la Base de Datos

El proyecto utiliza Docker Compose para levantar una instancia de PostgreSQL con las bases de datos necesarias configuradas automáticamente.

```bash
docker compose up -d
```

Esto ejecutará los scripts en `./init-db` para crear las bases de datos `auth` y `reto-tecnico` junto con sus tablas.

### 2. Construir los Proyectos

Desde la raíz del proyecto, puedes construir todos los módulos (si hubiera un pom padre, pero aquí parece que son proyectos independientes):

```bash
# En cada carpeta de microservicio:
./mvnw clean install
```

*Nota: Asegúrate de instalar primero `common-exception` ya que es una dependencia de otros módulos.*

```bash
cd common-exception
mvn install
```

### 3. Ejecutar los Microservicios

Debes ejecutar cada microservicio en el siguiente orden preferido:

1.  **server-oauth**: `cd server-oauth && ./mvnw spring-boot:run`
2.  **ms-cliente**: `cd ms-cliente && ./mvnw spring-boot:run`
3.  **ms-productos-financieros**: `cd ms-productos-financieros && ./mvnw spring-boot:run`
4.  **ms-bff**: `cd ms-bff && ./mvnw spring-boot:run`

## Funcionamiento Detallado

### Autenticación
El `server-oauth` emite un token JWT. Este token contiene un claim personalizado `uuid` que está encriptado. El `ms-bff` recibe este token, desencripta el `uuid` (que corresponde al ID de usuario de autenticación) y lo usa para consultar la información del cliente.

### BFF (Backend For Frontend)
El endpoint principal del BFF es `GET /api/v1/resumen`.
Este endpoint realiza lo siguiente:
1. Valida el token JWT.
2. Desencripta el ID de usuario.
3. Consulta al `ms-cliente` para obtener los datos básicos del cliente.
4. Con el ID interno del cliente, consulta al `ms-productos-financieros` para obtener la lista de sus productos.
5. Devuelve un objeto JSON consolidado.

### Trazabilidad
El sistema está configurado para enviar trazas a Zipkin (por defecto en `http://localhost:9411`). Esto permite seguir una petición desde el BFF hasta los microservicios de backend.

## Endpoints Principales

-   **BFF Resumen:** `GET http://localhost:8081/api/v1/resumen`
-   **Clientes:** `GET/POST http://localhost:8082/api/v1/clientes`
-   **Cuentas de Ahorro:** `GET/POST http://localhost:8083/cuentas-ahorro`
-   **Tarjetas de Crédito:** `GET/POST http://localhost:8083/tarjeta-credito`
-   **OAuth Token:** `POST http://localhost:9000/oauth2/token`

## Documentación API

Cada microservicio (ms-cliente y ms-productos-financieros) tiene integrado Swagger UI para explorar la API:
-   `http://localhost:8082/swagger-ui.html`
-   `http://localhost:8083/swagger-ui.html`
