-- Empleados
INSERT INTO empleados (nombre, email, legajo) VALUES ('Juan Pérez', 'juan@empresa.com', 1001);
INSERT INTO empleados (nombre, email, legajo) VALUES ('Ana López', 'ana@empresa.com', 2001);
INSERT INTO empleados (nombre, email, legajo) VALUES ('Carlos Ruiz', 'carlos@empresa.com', 3001);
INSERT INTO empleados (nombre, email, legajo) VALUES ('María García', 'maria@empresa.com', 1002);
INSERT INTO empleados (nombre, email, legajo) VALUES ('Pedro Gómez', 'pedro@empresa.com', 1003);

-- Encargados
INSERT INTO encargados (nombre, email, legajo, tipo, modo) VALUES ('Ana López', 'ana@empresa.com', 2001, 'RECEPCIONISTA', 'NORMAL');
INSERT INTO encargados (nombre, email, legajo, tipo, modo) VALUES ('Carlos Ruiz', 'carlos@empresa.com', 3001, 'SUPERVISOR', 'PRODUCTIVO');
INSERT INTO encargados (nombre, email, legajo, tipo, modo) VALUES ('Juan Pérez', 'juan@empresa.com', 1001, 'CEO', 'NORMAL');
INSERT INTO encargados (nombre, email, legajo, tipo, modo) VALUES ('María García', 'maria@empresa.com', 1002, 'GERENTE_RRHH', 'NORMAL');

-- Excusas (asumiendo que el empleado con legajo 1001 existe y la columna empleado_id es el id autogenerado)
-- Aquí se asume que los ids serán 1, 2, 3, 4, 5 en orden de inserción
INSERT INTO excusas (empleado_id, descripcion, tipo_excusa, estado, fecha_creacion) VALUES (1, 'Excusa trivial', 'TRIVIAL', 'PENDIENTE', CURRENT_TIMESTAMP);
INSERT INTO excusas (empleado_id, descripcion, tipo_excusa, estado, fecha_creacion) VALUES (2, 'Excusa moderada', 'MODERADA', 'PENDIENTE', CURRENT_TIMESTAMP);
INSERT INTO excusas (empleado_id, descripcion, tipo_excusa, estado, fecha_creacion) VALUES (3, 'Excusa compleja', 'COMPLEJA', 'PENDIENTE', CURRENT_TIMESTAMP);
INSERT INTO excusas (empleado_id, descripcion, tipo_excusa, estado, fecha_creacion) VALUES (4, 'Excusa inverosímil', 'INVEROSIMIL', 'PENDIENTE', CURRENT_TIMESTAMP); 