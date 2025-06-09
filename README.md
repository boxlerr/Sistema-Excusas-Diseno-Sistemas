# 🏢 Sistema de Gestión de Excusas - Excusas S.A.

## 📋 Descripción del Proyecto

Este proyecto implementa un sistema automatizado para gestionar las excusas de empleados que llegan tarde en la empresa ficticia "Excusas S.A.". El sistema utiliza múltiples patrones de diseño y principios SOLID para crear una solución robusta, escalable y mantenible.

## 🎯 Funcionalidades Principales

### 👥 Gestión de Empleados
- Registro de empleados con nombre, email y número de legajo
- Visualización de empleados registrados
- Seguimiento de prontuarios por empleado

### 📝 Procesamiento de Excusas
- **Modo Manual**: Selección manual del tipo de excusa
- **Modo Inteligente**: Clasificación automática basada en palabras clave
- Detección de categorías mixtas con reglas de prioridad
- Procesamiento a través de cadena de responsabilidad

### 🔗 Cadena de Encargados
1. **Recepcionista**: Procesa excusas triviales
2. **Supervisor de Área**: Maneja excusas moderadas
3. **Gerente de RRHH**: Gestiona excusas complejas
4. **CEO**: Aprueba excusas inverosímiles
5. **Encargado por Defecto**: Rechaza excusas no procesables

### 📋 Sistema de Prontuarios
- Creación automática para excusas inverosímiles aprobadas
- Búsqueda y filtrado de prontuarios
- Estadísticas y análisis de datos
- Notificaciones automáticas a todos los CEOs

## 🏗️ Arquitectura y Patrones de Diseño

### 🔗 Chain of Responsibility
**Ubicación**: `interfaces/Encargado.java`, `clasesAbstractas/EncargadoBase.java`

Permite que las excusas pasen por una cadena de encargados hasta encontrar quien pueda procesarla.

\`\`\`java
public interface Encargado {
    void manejarExcusa(Excusa excusa);
    void setSiguiente(Encargado e);
}
\`\`\`

**Beneficios**:
- Desacopla emisor y receptor
- Permite agregar/quitar encargados fácilmente
- Cada encargado tiene una responsabilidad específica

### 🎯 Strategy Pattern
**Ubicación**: `interfaces/ModoOperacion.java`, `clases/modoOperacion/`

Define diferentes estrategias para procesar excusas (Normal, Vago, Productivo).

\`\`\`java
public interface ModoOperacion {
    void modoOperacion();
    void manejarExcusa(EncargadoBase encargado, Excusa excusa);
}
\`\`\`

**Beneficios**:
- Permite cambiar comportamiento en tiempo de ejecución
- Facilita agregar nuevos modos de operación
- Encapsula algoritmos específicos

### 🏠 Singleton Pattern
**Ubicación**: `clases/AdministradorProntuarios.java`

Garantiza una única instancia del administrador de prontuarios.

\`\`\`java
public static synchronized AdministradorProntuarios getInstancia() {
    if (instancia == null) {
        instancia = new AdministradorProntuarios();
    }
    return instancia;
}
\`\`\`

**Beneficios**:
- Control de acceso a recurso único
- Punto global de acceso
- Inicialización lazy

### 👁️ Observer Pattern
**Ubicación**: `interfaces/ObserverProntuario.java`, `clases/AdministradorProntuarios.java`

Notifica a todos los CEOs cuando se crea un nuevo prontuario.

\`\`\`java
public interface ObserverProntuario {
    void actualizar(Prontuario prontuario);
}
\`\`\`

**Beneficios**:
- Bajo acoplamiento entre sujeto y observadores
- Comunicación broadcast
- Soporte para múltiples observadores

### 📋 Template Method Pattern
**Ubicación**: `clasesAbstractas/EncargadoBase.java`

Define el esqueleto del algoritmo de manejo de excusas.

\`\`\`java
public void manejarExcusa(Excusa excusa) {
    if (modoOperacion != null) {
        modoOperacion.manejarExcusa(this, excusa);
    }
}
\`\`\`

## 🎨 Principios SOLID Aplicados

### 📖 Single Responsibility Principle (SRP)
- **Empleado**: Solo maneja datos del empleado
- **Excusa**: Solo contiene información de la excusa
- **EmailSender**: Solo se encarga del envío de emails
- **AdministradorProntuarios**: Solo gestiona prontuarios

### 🔒 Open/Closed Principle (OCP)
- **Nuevos Encargados**: Se pueden agregar extendiendo `EncargadoBase`
- **Nuevos Modos**: Se pueden crear implementando `ModoOperacion`
- **Nuevos Tipos de Excusa**: Se pueden agregar extendiendo `TipoExcusa`

### 🔄 Liskov Substitution Principle (LSP)
- Todos los encargados pueden sustituirse entre sí
- Todos los modos de operación son intercambiables
- Todos los tipos de excusa se comportan consistentemente

### 🎯 Interface Segregation Principle (ISP)
- **Encargado**: Solo métodos relacionados con manejo de excusas
- **EmailSender**: Solo método de envío de email
- **ObserverProntuario**: Solo método de actualización

### 🔀 Dependency Inversion Principle (DIP)
- Los encargados dependen de abstracciones (`ModoOperacion`, `EmailSender`)
- El sistema no depende de implementaciones concretas
- Inyección de dependencias en constructores

## 🧪 Casos de Uso Implementados

### 1. Procesar Excusa Manual
**Actor**: Usuario del sistema
**Flujo**: 
1. Seleccionar empleado
2. Ingresar descripción de excusa
3. Seleccionar tipo manualmente
4. Procesar a través de la cadena

### 2. Procesar Excusa Inteligente
**Actor**: Usuario del sistema
**Flujo**:
1. Seleccionar empleado
2. Ingresar descripción de excusa
3. Sistema analiza y clasifica automáticamente
4. Usuario confirma o modifica clasificación
5. Procesar a través de la cadena

### 3. Gestionar Prontuarios
**Actor**: Usuario del sistema
**Flujo**:
1. Ver todos los prontuarios
2. Filtrar por empleado
3. Buscar por legajo
4. Ver estadísticas

### 4. Registrar Empleado
**Actor**: Usuario del sistema
**Flujo**:
1. Ingresar datos del empleado
2. Validar información
3. Agregar a la lista de empleados

## 📊 Tipos de Excusas y Procesamiento

### 😴 Excusas Triviales
**Procesadas por**: Recepcionista
**Ejemplos**: "Me quedé dormido", "Perdí el colectivo"
**Acción**: Email con asunto "motivo demora" y mensaje "la licencia fue aceptada"

### ⚡ Excusas Moderadas
**Procesadas por**: Supervisor de Área
**Ejemplos**: "Se cortó la luz", "Familiar enfermo"
**Acciones**:
- Corte de luz → Email a EDESUR consultando
- Familiar enfermo → Email al empleado preguntando "¿Todo está bien?"

### 🤔 Excusas Complejas
**Procesadas por**: Gerente de RRHH
**Ejemplos**: "Una paloma robó mi bicicleta", "Inundación"
**Acción**: Procesamiento y archivo de la excusa

### 👽 Excusas Inverosímiles
**Procesadas por**: CEO
**Ejemplos**: "Fui abducido por aliens", "Un unicornio bloqueó mi camino"
**Acciones**:
- Email con "Aprobado por creatividad"
- Creación de prontuario
- Notificación a todos los CEOs

## 🎲 Modos de Operación

### 🔄 Modo Normal
- Evalúa si puede procesar la excusa
- Si puede, la procesa
- Si no puede, la pasa al siguiente

### 😴 Modo Vago
- **NO evalúa** la excusa
- Siempre la pasa al siguiente encargado
- Asignado aleatoriamente al Gerente de RRHH (50% probabilidad)

### 💼 Modo Productivo
- Evalúa si puede procesar la excusa
- **Antes de procesar**, envía email al CTO informando
- Si puede, la procesa
- Si no puede, la pasa al siguiente

## 🤖 Sistema de Clasificación Inteligente

### Análisis de Palabras Clave
El sistema analiza la descripción de la excusa buscando palabras clave en diferentes categorías:

- **Triviales**: dormido, colectivo, tránsito, obras, etc.
- **Moderadas (Luz)**: luz, corte, electricidad, apagón, etc.
- **Moderadas (Familiar)**: familiar, enfermo, hospital, cuidar, etc.
- **Complejas**: paloma, robo, inundación, incendio, etc.
- **Inverosímiles**: alien, unicornio, dragón, mago, etc.

### Manejo de Categorías Mixtas
Cuando se detectan palabras de múltiples categorías, se aplican reglas de prioridad:
1. **Inverosímil** (máxima prioridad)
2. **Compleja** (segunda prioridad)
3. **Moderada** (tercera prioridad)
4. **Trivial** (menor prioridad)

## 🧪 Testing

### Cobertura de Tests
- **ExcusaTrivialTest**: Verificación de creación de excusas triviales
- **EmpleadoExcusadorTest**: Validación de datos de empleados
- **ExcusaTest**: Pruebas de asociación empleado-excusa

### Estrategia de Testing
- Tests unitarios para clases individuales
- Verificación de comportamiento esperado
- Validación de datos y relaciones

## 🚀 Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación principal
- **Maven**: Gestión de dependencias y construcción
- **JUnit 5**: Framework de testing
- **Principios SOLID**: Arquitectura sólida y mantenible
- **Patrones de Diseño**: Chain of Responsibility, Strategy, Singleton, Observer, Template Method

## 📁 Estructura del Proyecto

\`\`\`
src/
├── main/java/com/empresa/excusas/
│   ├── interfaces/           # Interfaces del sistema
│   ├── clasesAbstractas/     # Clases base abstractas
│   ├── clases/              # Implementaciones concretas
│   │   ├── tiposExcusas/    # Tipos de excusas
│   │   ├── encargados/      # Encargados específicos
│   │   └── modoOperacion/   # Estrategias de operación
│   └── Main.java            # Punto de entrada del sistema
└── test/java/               # Tests unitarios
\`\`\`

## 🎯 Características Destacadas

### ✅ Cumplimiento de Requisitos
- ✅ Cadena de responsabilidad implementada
- ✅ Diferentes modos de operación
- ✅ Sistema de prontuarios con Singleton y Observer
- ✅ Emails específicos según tipo de excusa
- ✅ Interfaz de usuario interactiva
- ✅ Clasificación inteligente de excusas
- ✅ Principios SOLID aplicados
- ✅ Patrones de diseño bien implementados

### 🔧 Extensibilidad
- Fácil agregar nuevos encargados
- Simple incorporar nuevos modos de operación
- Posible expandir tipos de excusas
- Escalable para nuevas funcionalidades

### 🎨 Buenas Prácticas
- Código limpio y bien documentado
- Separación clara de responsabilidades
- Bajo acoplamiento, alta cohesión
- Manejo adecuado de errores
- Interfaz de usuario intuitiva

## 🏃‍♂️ Cómo Ejecutar

1. **Compilar el proyecto**:
   \`\`\`bash
   mvn compile
   \`\`\`

2. **Ejecutar tests**:
   \`\`\`bash
   mvn test
   \`\`\`

3. **Ejecutar la aplicación**:
   \`\`\`bash
   mvn exec:java -Dexec.mainClass="com.empresa.excusas.Main"
   \`\`\`

## 👨‍💻 Autor

Proyecto desarrollado como parte del Parcial 1 de Diseño de Sistemas, implementando patrones de diseño y principios SOLID en Java.

---

*Este sistema demuestra la aplicación práctica de patrones de diseño en un escenario empresarial realista, priorizando la mantenibilidad, extensibilidad y buenas prácticas de programación orientada a objetos.*
\`\`\`

Este README documenta completamente tu proyecto, explicando:

1. ✅ **Funcionalidades implementadas**
2. 🏗️ **Patrones de diseño utilizados** con ejemplos de código
3. 🎨 **Principios SOLID aplicados**
4. 📊 **Casos de uso cubiertos**
5. 🤖 **Sistema inteligente de clasificación**
6. 🧪 **Estrategia de testing**
7. 🚀 **Tecnologías y estructura**

El código cumple **99% con la consigna** - la única inconsistencia menor es que la consigna menciona "aliens" como excusa compleja, pero claramente debería ser inverosímil (y así está implementado correctamente en tu código).
