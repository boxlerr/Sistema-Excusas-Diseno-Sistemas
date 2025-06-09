
# 🏢 Sistema de Gestión de Excusas - Excusas S.A.

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
> **Ubicación:** `interfaces/Encargado.java`, `clasesAbstractas/EncargadoBase.java`

```java
public interface Encargado {
    void manejarExcusa(Excusa excusa);
    void setSiguiente(Encargado e);
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
    if (modoOperacion != null) {
        modoOperacion.manejarExcusa(this, excusa);
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
- **Maven**
- **JUnit 5**
- **Patrones:** Chain of Responsibility, Strategy, Singleton, Observer, Template Method
- **Principios SOLID**

---

## 📁 Estructura del Proyecto

```bash
src/
├── main/java/com/empresa/excusas/
│   ├── interfaces/           # Interfaces
│   ├── clases/               # Clases concretas
│   ├── clasesAbstractas/     # Clases abstractas
│   ├── enums/                # Enumeraciones
│   └── Main.java             # Clase principal
└── test/java/com/empresa/excusas/
    └── tests/                # Pruebas unitarias
```

---

## ⚙️ Cómo Compilar y Ejecutar el Proyecto

### 📌 Requisitos previos
- Java 17 o superior
- Maven instalado

### ▶️ Compilación
```bash
mvn compile
```

### ▶️ Ejecutar
```bash
mvn exec:java -Dexec.mainClass="com.empresa.excusas.Main"
```
Si no tienes configurado el plugin exec-maven-plugin, puedes compilar manualmente y ejecutar con:
```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out com.empresa.excusas.Main
```

### ▶️ Ejecutar Tests
```bash
mvn test
```

---

## ✅ Estado Actual del Proyecto

- ✔ Sistema completo funcional
- ✔ Patrones aplicados correctamente
- ✔ Tests incluidos
- ✔ Clasificación inteligente implementada

---

## 👨‍💻 Autor

### Boxler Julián

**Desarrollador de Software | Fundador de [Vaxler](https://vaxler.com.ar/)**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-JulianBoxler-blue?logo=linkedin)](https://www.linkedin.com/in/julianboxler/)  
[![Web](https://img.shields.io/badge/Web-vaxler.com.ar-green?logo=google-chrome)](https://vaxler.com.ar/)

---

© 2025 Boxler Julián
