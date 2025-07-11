# 🏢 Sistema de Gestión de Excusas - Excusas S.A.

## 📋 Descripción General

Este proyecto implementa una API RESTful para la gestión de excusas de empleados en la empresa ficticia **"Excusas S.A."**. El sistema permite registrar excusas, consultar su estado, ver el historial de prontuarios y administrar la línea de encargados en tiempo real. Se aplican principios SOLID, patrones de diseño y buenas prácticas de arquitectura.

---

## 🎯 Funcionalidades Principales

- **Gestión de empleados:** Alta, consulta y búsqueda.
- **Registro y consulta de excusas:** Alta, consulta por empleado, filtros por fecha, motivo, encargado y estado.
- **Cadena de encargados:** Evaluación automática de excusas usando Chain of Responsibility.
- **Gestión de prontuarios:** Consulta y filtrado de prontuarios generados por CEOs.
- **Administración dinámica de encargados:** Alta y cambio de modo de evaluación.
- **Eliminación segura de excusas:** Solo si se provee fecha límite.
- **Manejo global de errores:** Respuestas estandarizadas y códigos HTTP adecuados.
- **Persistencia en H2/JPA:** Empleados, excusas y prontuarios.
- **Tests automáticos:** Validación de casos de éxito y error.

---

## 🏗️ Arquitectura

- **Controladores:** Exponen los endpoints REST y delegan la lógica.
- **Servicios:** Contienen la lógica de negocio.
- **Modelos:** Entidades y enums.
- **Repositorios:** Acceso a datos con JPA.
- **DTOs:** Entrada y salida de datos.
- **Manejo de errores:** `GlobalExceptionHandler` y `ApiError`.

---

## 🔗 Endpoints REST Principales

| Método | Endpoint                                      | Descripción                                                                 |
|--------|-----------------------------------------------|-----------------------------------------------------------------------------|
| GET    | `/api/prontuarios`                           | Listar todos los prontuarios generados por CEOs                             |
| GET    | `/api/empleados`                             | Listar todos los empleados                                                  |
| POST   | `/api/empleados`                             | Registrar un nuevo empleado                                                 |
| GET    | `/api/excusas`                               | Listar todas las excusas (filtros por tipo, estado, fecha, encargado)       |
| POST   | `/api/excusas`                               | Registrar una excusa (motivo como string, validación de tipo)               |
| GET    | `/api/excusas/empleado/{legajo}`             | Listar excusas de un empleado específico                                    |
| DELETE | `/api/excusas/eliminar?fechaLimite=...`      | Eliminar excusas antes de una fecha (parámetro obligatorio)                 |
| GET    | `/api/encargados`                            | Listar la configuración actual de la línea de encargados                    |
| POST   | `/api/encargados`                            | Agregar un nuevo encargado dinámicamente                                    |
| PUT    | `/api/encargados/{legajo}/modo`              | Cambiar el modo de evaluación de un encargado                               |

---

## 🛡️ Manejo de Errores

- **GlobalExceptionHandler:** Captura excepciones y responde con objetos `ApiError` y códigos HTTP claros.
- **Validaciones:**  
  - No se puede registrar excusa sin tipo válido (400).
  - No se puede eliminar excusas sin fecha límite (400).
  - Respuestas 404 para recursos no encontrados.
- **Tests automáticos:** Verifican el correcto manejo de errores y respuestas.

---

## ❌ Ejemplos de Respuestas de Error

### Error: Registrar excusa con tipo inválido

**Request:**
```http
POST http://localhost:8080/api/excusas
Content-Type: application/json

{
  "legajoEmpleado": 1001,
  "descripcion": "Llegué tarde por ovnis",
  "tipoExcusa": "INVENTADA"
}
```

**Respuesta:**
```json
{
  "timestamp": "2024-06-10T15:23:45.123",
  "status": 400,
  "error": "Bad Request",
  "message": "Tipo de excusa inválido: INVENTADA",
  "path": "/api/excusas"
}
```

---

### Error: Eliminar excusas sin fecha límite

**Request:**
```http
DELETE http://localhost:8080/api/excusas/eliminar
```

**Respuesta:**
```json
{
  "timestamp": "2024-06-10T15:25:12.456",
  "status": 400,
  "error": "Bad Request",
  "message": "Debe proporcionar fechaLimite",
  "path": "/api/excusas/eliminar"
}
```

---

### Error: Consultar empleado inexistente

**Request:**
```http
GET http://localhost:8080/api/empleados/9999
```

**Respuesta:**
```json
{
  "timestamp": "2024-06-10T15:27:01.789",
  "status": 404,
  "error": "Not Found",
  "message": "Empleado no encontrado",
  "path": "/api/empleados/9999"
}
```

---

## 🧪 Testing

- **JUnit 5** para pruebas unitarias e integrales.
- Tests de controladores y servicios, incluyendo casos de error.
- Ejemplo: `ExcusaControllerErrorTest.java` valida respuestas ante datos inválidos.

---

## 🗃️ Persistencia

- **JPA/H2:** Persistencia en memoria para empleados, excusas y prontuarios.
- **Configuración:** Ver `application.properties`.

---

## 🏛️ Patrones y Principios

- **Chain of Responsibility:** Línea de encargados para procesar excusas.
- **Strategy:** Modos de operación de encargados (NORMAL, VAGO, PRODUCTIVO).
- **Singleton:** Administrador de prontuarios.
- **Observer:** Notificaciones a CEOs.
- **Template Method:** Flujo de manejo de excusas.
- **SOLID, DRY, Tell Don’t Ask:** Arquitectura limpia y desacoplada.

---

## 📁 Estructura del Proyecto

```
src/main/java/com/empresa/excusas/
├── controller/      # Controladores REST
├── model/           # Entidades, enums, interfaces y clases abstractas
├── repository/      # Repositorios JPA
├── service/         # Servicios de negocio
```

---

## 🚀 Tecnologías Utilizadas

- Java 17
- Spring Boot
- Maven
- JPA/Hibernate
- H2 Database
- JUnit 5

---

## 📄 Diagramas

> **Recuerda:** Los diagramas de clases, casos de uso y arquitectura deben adjuntarse en la entrega comprimida.

---

## 📚 Ejemplos de Uso de la API

### Registrar un empleado

```http
POST http://localhost:8080/api/empleados
Content-Type: application/json

{
  "nombre": "Juancito Perez Vintage",
  "email": "juanceto01@vaxler.com",
  "legajo": 1001
}
```

### Listar empleados

```http
GET http://localhost:8080/api/empleados
```

### Registrar una excusa

```http
POST http://localhost:8080/api/excusas
Content-Type: application/json

{
  "legajoEmpleado": 1001,
  "descripcion": "Llegué tarde por corte de luz",
  "tipoExcusa": "MODERADA"
}
```

### Eliminar excusas antes de una fecha

```http
DELETE http://localhost:8080/api/excusas/eliminar?fechaLimite=2024-06-01T00:00:00
```

### Cambiar el modo de un encargado

```http
PUT http://localhost:8080/api/encargados/1001/modo
Content-Type: application/json

{
  "modo": "PRODUCTIVO"
}
```

---

## ▶️ Cómo correr el proyecto localmente

1. **Requisitos previos:**
   - Java 17 o superior instalado
   - Maven instalado

2. **Clonar el repositorio:**
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd parcial1DisenioSistemas
   ```

3. **Compilar y ejecutar la aplicación:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Acceso a la API:**
   - La API estará disponible en: [http://localhost:8080](http://localhost:8080)
   - La consola de la base de datos H2 estará disponible en: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
     - JDBC URL: `jdbc:h2:mem:excusasdb`
     - Usuario: `sa`
     - Contraseña: `password`

5. **Ejecutar los tests:**
   ```bash
   mvn test
   ```

---

## 👤 Autor

Boxler Julián
