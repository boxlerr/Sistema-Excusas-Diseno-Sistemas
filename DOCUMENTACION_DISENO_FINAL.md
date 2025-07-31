# Documentación de Diseño - Sistema de Excusas S.A.

## Parte A - Sistema de Auditoría

### Patrón Implementado: Decorator + Factory

#### Justificación de las Decisiones de Diseño

1. **Principio Abierto/Cerrado**: Se implementó el patrón Decorator para extender el comportamiento de los encargados sin modificar su código original. Esto permite agregar funcionalidad de auditoría a cualquier encargado existente sin romper el principio de abierto/cerrado.

2. **Composición sobre Herencia**: Se utilizó composición para envolver los encargados existentes con funcionalidad de auditoría, en lugar de crear nuevas clases que hereden de las existentes.

3. **Separación de Responsabilidades**: 
   - `IAuditoria`: Define el contrato para el sistema de auditoría
   - `AuditoriaService`: Implementa la lógica de registro de auditoría
   - `EncargadoConAuditoria`: Decorador que agrega funcionalidad de auditoría
   - `EncargadoFactory`: Factory para crear encargados con auditoría automáticamente

4. **Flexibilidad**: El sistema permite activar/desactivar la auditoría simplemente usando el factory o creando encargados directamente.

#### Estructura del Patrón

```
IEncargado (Interface)
├── EncargadoBase (Clase abstracta)
│   ├── Recepcionista
│   ├── SupervisorArea
│   ├── GerenteRRHH
│   ├── CEO
│   └── EncargadoPorDefecto
└── EncargadoConAuditoria (Decorador)
    └── envuelve cualquier IEncargado
```

#### Flujo de Auditoría

1. Se crea un encargado usando `EncargadoFactory`
2. El factory envuelve el encargado con `EncargadoConAuditoria`
3. Cuando se procesa una excusa, el decorador:
   - Registra auditoría antes del procesamiento
   - Delega el procesamiento al encargado original
   - Determina el resultado basado en el estado de la excusa
   - Registra el log de auditoría

#### Ejemplo de Log Esperado

```
[AUDITORÍA] Encargado: Recepcionista - Excusa: ME_QUEDE_DORMIDO - Resultado: ACEPTADA
[AUDITORÍA] Encargado: Supervisor - Excusa: CUIDADO_FAMILIAR - Resultado: DERIVADA
```

## Parte B - Extensión de la API

### Nuevo Endpoint Implementado

#### Endpoint: `GET /api/excusas/empleado/{legajo}/filtros`

**Parámetros:**
- `legajo` (path): ID del empleado (requerido)
- `entre` (query): Fecha desde (opcional, formato ISO)
- `hasta` (query): Fecha hasta (opcional, formato ISO)

**Comportamiento:**
- Si solo se proporciona `entre`: trae excusas posteriores a esa fecha
- Si solo se proporciona `hasta`: trae excusas anteriores a esa fecha
- Si se proporcionan ambos: trae excusas en el rango especificado
- Si no se proporciona ninguno: retorna error indicando que son parámetros necesarios

#### Ejemplos de Uso

```bash
# Solo fecha desde
GET /api/excusas/empleado/1001/filtros?entre=2024-01-01T00:00:00

# Solo fecha hasta
GET /api/excusas/empleado/1001/filtros?hasta=2024-12-31T23:59:59

# Rango completo
GET /api/excusas/empleado/1001/filtros?entre=2024-01-01T00:00:00&hasta=2024-12-31T23:59:59

# Sin parámetros (error)
GET /api/excusas/empleado/1001/filtros
```

#### Implementación Técnica

1. **Repository Layer**: Se agregaron métodos específicos para cada caso de filtrado
2. **Service Layer**: Lógica de validación y delegación a los métodos del repository
3. **Controller Layer**: Manejo de parámetros opcionales y respuestas de error

#### Validaciones Implementadas

- Verificación de que al menos un parámetro de fecha esté presente
- Verificación de que el empleado existe
- Manejo de errores con respuestas HTTP apropiadas

## Ventajas del Diseño Implementado

### Parte A - Auditoría
- ✅ **Extensibilidad**: Fácil agregar nuevos tipos de auditoría
- ✅ **Reutilización**: Cualquier encargado puede usar auditoría
- ✅ **Mantenibilidad**: Código original no se modifica
- ✅ **Testabilidad**: Cada componente puede probarse independientemente

### Parte B - API
- ✅ **Flexibilidad**: Parámetros opcionales permiten múltiples casos de uso
- ✅ **Validación**: Manejo robusto de errores
- ✅ **Escalabilidad**: Fácil agregar nuevos filtros
- ✅ **Documentación**: Endpoint bien documentado y predecible

## Consideraciones de Rendimiento

- Los logs de auditoría se imprimen en consola (en producción se podría usar un sistema de logging)
- Las consultas de base de datos están optimizadas con índices apropiados
- Se utilizan transacciones de solo lectura para consultas 