# Desafío - Capa de acceso de datos (II)


#### Tabla usada  

```sql
-- Eliminar la tabla proveedores si ya existe
DROP TABLE IF EXISTS proveedores;

-- Crear la tabla proveedores
CREATE TABLE proveedores (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    rut VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    contacto VARCHAR(255) NOT NULL,
    telefono_contacto VARCHAR(20) NOT NULL
);

-- Insertar datos de prueba
INSERT INTO proveedores (nombre, rut, direccion, correo, telefono, contacto, telefono_contacto) VALUES
('Proveedor 1', '12345678-9', 'Direccion 1', 'correo1@example.com', '123456789', 'Contacto 1', '987654321'),
('Proveedor 2', '23456789-0', 'Direccion 2', 'correo2@example.com', '234567890', 'Contacto 2', '876543210'),
('Proveedor 3', '34567890-1', 'Direccion 3', 'correo3@example.com', '345678901', 'Contacto 3', '765432109'),
('Proveedor 4', '45678901-2', 'Direccion 4', 'correo4@example.com', '456789012', 'Contacto 4', '654321098'),
('Proveedor 5', '56789012-3', 'Direccion 5', 'correo5@example.com', '567890123', 'Contacto 5', '543210987');

```
  
  
**Configuracion BD**  
[https://github.com/asmitmans/Desafio5_M5_U4/blob/main/src/main/java/cl/fullstackjava/model/conexion/Conexion.java](https://github.com/asmitmans/Desafio5_M5_U4/blob/main/src/main/java/cl/fullstackjava/model/conexion/Conexion.java)  

