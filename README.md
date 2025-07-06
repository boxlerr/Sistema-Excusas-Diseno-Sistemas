# 🏢 Sistema de Gestión de Excusas - Excusas S.A.

## 📋 Información del Proyecto

**Repositorio:** https://github.com/boxlerr/Sistema-Excusas-Diseno-Sistemas
**Integrantes:** Julián Boxler

---

Este proyecto implementa un sistema automatizado para gestionar las excusas de empleados que llegan tarde en la empresa ficticia **"Excusas S.A."**.  
El sistema utiliza múltiples **patrones de diseño** y **principios SOLID** para crear una solución robusta, escalable y mantenible.

---

## 🎯 Funcionalidades Principales

### 👥 Gestión de Empleados
- Registro de empleados con nombre, email y número de legajo
- Visualización de empleados registrados
- Seguimiento de prontuarios por empleado

### 📝 Procesamiento de Excusas
- **Modo Manual**: Selección manual del tipo de excusa
- **Modo Inteligente**: Clasificación automática basada en palabras clave
- Procesamiento a través de cadena de responsabilidad
- Detección de categorías mixtas con reglas de prioridad

### 🔗 Cadena de Encargados
| Encargado             | Función                                |
| --------------------- | -------------------------------------- |
| Recepcionista         | Procesa excusas triviales             |
| Supervisor de Área    | Maneja excusas moderadas              |
| Gerente de RRHH       | Gestiona excusas complejas            |
| CEO                   | Aprueba excusas inverosímiles         |
| Encargado por Defecto | Rechaza excusas no procesables        |

### 📋 Sistema de Prontuarios
- Creación automática para excusas inverosímiles aprobadas
- Búsqueda y filtrado de prontuarios
- Estadísticas y análisis de datos
- Notificaciones automáticas a todos los CEOs

---

## 🏗️ Arquitectura y Patrones de Diseño

### 🔗 Chain of Responsibility
> **Ubicación:** `interfaces/IEncargado.java`, `clasesAbstractas/EncargadoBase.java`

```java
public interface IEncargado {
    void manejarExcusa(Excusa excusa);
    void setSiguiente(IEncargado e);
    boolean puedeManejar(Excusa excusa);
    void procesar(Excusa excusa);
}
```

**Beneficios:**
- Desacopla emisor y receptor
- Permite agregar/quitar encargados fácilmente
- Cada encargado tiene una responsabilidad específica

### 🎯 Strategy Pattern
> **Ubicación:** `interfaces/ModoOperacion.java`, `clases/modoOperacion/`

```java
public interface ModoOperacion {
    void modoOperacion();
    void manejarExcusa(EncargadoBase encargado, Excusa excusa);
}
```

**Beneficios:**
- Permite cambiar comportamiento en tiempo de ejecución
- Facilita agregar nuevos modos de operación
- Encapsula algoritmos específicos

### 🏠 Singleton Pattern
> **Ubicación:** `clases/AdministradorProntuarios.java`

```java
public static synchronized AdministradorProntuarios getInstancia() {
    if (instancia == null) {
        instancia = new AdministradorProntuarios();
    }
    return instancia;
}
```

**Beneficios:**
- Control de acceso a recurso único
- Punto global de acceso
- Inicialización lazy

### 👁️ Observer Pattern
> **Ubicación:** `interfaces/ObserverProntuario.java`, `clases/AdministradorProntuarios.java`

```java
public interface ObserverProntuario {
    void actualizar(Prontuario prontuario);
}
```

**Beneficios:**
- Bajo acoplamiento entre sujeto y observadores
- Comunicación broadcast
- Soporte para múltiples observadores

### 📋 Template Method Pattern
> **Ubicación:** `clasesAbstractas/EncargadoBase.java`

```java
public void manejarExcusa(Excusa excusa) {
    if (puedeManejar(excusa)) {
        procesar(excusa);
    } else if (siguiente != null) {
        siguiente.manejarExcusa(excusa);
    }
}
```

---

## 🎨 Principios SOLID Aplicados

- **SRP**: Cada clase tiene una única responsabilidad.
- **OCP**: Extensible sin modificar código existente.
- **LSP**: Clases hijas pueden reemplazar a sus padres.
- **ISP**: Interfaces específicas, pequeñas y cohesionadas.
- **DIP**: Dependencias orientadas a abstracciones.

---

## ✅ Casos de Uso Implementados

- ✅ Procesar Excusa Manual
- ✅ Procesar Excusa Inteligente
- ✅ Gestionar Prontuarios
- ✅ Registrar Empleado

---

## 📊 Tipos de Excusas y Procesamiento

| Tipo                | Encargado          | Ejemplos                            | Acción                                         |
| ------------------- | ------------------ | ----------------------------------- | --------------------------------------------- |
| **Triviales**       | Recepcionista      | "Dormido", "Perdí el colectivo"     | Email "La licencia fue aceptada"             |
| **Moderadas (Luz)** | Supervisor de Área | "Corte de luz", "Electricidad"      | Email a EDESUR                              |
| **Moderadas (Fam.)**| Supervisor de Área | "Familiar enfermo"                  | Email al empleado                           |
| **Complejas**       | Gerente de RRHH    | "Paloma robó bicicleta", "Inundación" | Procesamiento y archivo                     |
| **Inverosímiles**   | CEO                | "Abducido por aliens"               | Email creativo, prontuario, notificación CEOs |

---

## 🎲 Modos de Operación

| Modo        | Comportamiento                                               |
| ----------- | ------------------------------------------------------------ |
| **Normal**  | Evalúa → Procesa o pasa al siguiente                          |
| **Vago**    | No evalúa → Siempre pasa al siguiente                         |
| **Productivo** | Evalúa → Envía email → Procesa o pasa al siguiente         |

---

## 🤖 Sistema de Clasificación Inteligente

- Analiza palabras clave para asignar automáticamente la categoría correspondiente.
- **Prioridad de categorías:** Inverosímil > Compleja > Moderada > Trivial

---

## 🧪 Testing

- Tests implementados con **JUnit 5**
- Clases principales probadas:
  - `ExcusaTrivialTest`
  - `EmpleadoExcusadorTest`
  - `ExcusaTest`

---

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.5.3**
- **Maven**
- **JUnit 5**
- **H2 Database**
- **Patrones:** Chain of Responsibility, Strategy, Singleton, Observer, Template Method

---

## 📁 Estructura del Proyecto

```
src/main/java/com/empresa/excusas/
├── interfaces/           # Interfaces del sistema
├── clasesAbstractas/     # Clases abstractas base
├── clases/              # Implementaciones concretas
│   ├── encargados/      # Cadena de encargados
│   ├── modoOperacion/   # Estrategias de operación
│   └── tiposExcusas/    # Tipos de excusa
└── TestSistema.java     # Clase de prueba principal
```

---

## 🎯 Milestones del Proyecto

1. **Diseño del sistema**: Crear el modelo de clases, relaciones e interfaces respetando OOP y SOLID.
2. **Implementación de la cadena de encargados**: Codificar la lógica de manejo de excusas con la cadena de responsabilidad.
3. **Lógica de emails y comunicación**: Implementar el envío de emails según el comportamiento de cada encargado.
4. **Registro de prontuarios**: Implementar el administrador de prontuarios y su uso por el CEO.
5. **Tests del sistema**: Agregar pruebas unitarias con JUnit 5 para cubrir comportamiento esperado.
6. **Diagramas y documentación**: Crear y entregar los diagramas UML y justificaciones de patrones.
7. **Exposición del sistema vía API REST**: Implementar una API RESTful con Spring Boot para permitir que se ingresen excusas desde el exterior.

---

## 🏷️ Labels del Proyecto

- **bug**: Para errores detectados en la lógica del sistema
- **feature**: Para nuevas funcionalidades
- **refactor**: Para reestructurar el código sin alterar el comportamiento
- **oop**: Cambios que responden a principios de programación orientada a objetos
- **solid**: Aplicación de principios SOLID
- **chain-of-responsibility**: Cambios relacionados con la cadena de encargados
- **email**: Funcionalidades asociadas al envío de emails
- **prontuario**: Tareas vinculadas al registro o persistencia de prontuarios
- **tests**: Implementación o mejora de pruebas unitarias
- **api**: Tareas vinculadas a la creación o mejora de la API REST con Spring Boot
- **controller**: Tareas relacionadas con endpoints y controladores REST
- **service**: Lógica de negocio dentro de servicios de Spring
- **documentation**: Diagramas, README, explicaciones de patrones, API Docs
- **uml**: Cambios específicos sobre diagramas de clases o casos de uso
- **help wanted**: Si necesitas asistencia externa en un issue
- **enhancement**: Mejoras en la calidad del sistema

---

© 2025 Boxler Julián
