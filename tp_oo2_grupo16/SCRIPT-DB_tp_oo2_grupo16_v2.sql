-- Crear base de datos
CREATE DATABASE IF NOT EXISTS tp_oo2_grupo16_v2;
USE tp_oo2_grupo16_v2;

-- Crear tabla persona (abstracta)
CREATE TABLE persona (
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE
);

-- Crear tabla cliente (hereda de persona)
CREATE TABLE cliente (
    id_persona INT PRIMARY KEY,
    concurrente BOOLEAN NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- Crear tabla profesional (hereda de persona)
CREATE TABLE profesional (
    id_persona INT PRIMARY KEY,
    matricula VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- Tabla de contacto para cliente (relación 1 a 1 con cascada)
CREATE TABLE contacto (
    id_contacto INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_persona)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- Crear tabla especialidad
CREATE TABLE especialidad (
    id_especialidad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- Tabla intermedia para la relación muchos a muchos entre profesional y especialidad
CREATE TABLE profesional_especialidad (
    id_profesional INT,
    id_especialidad INT,
    PRIMARY KEY (id_profesional, id_especialidad),
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_persona)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (id_especialidad) REFERENCES especialidad(id_especialidad)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- Tabla Servicio (nueva)
CREATE TABLE servicio (
    id_servicio INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

-- Tabla Turno (nueva)
CREATE TABLE turno (
    id_turno INT AUTO_INCREMENT PRIMARY KEY,
    id_profesional INT NOT NULL,
    id_cliente INT NOT NULL,
    fecha_hora DATETIME NOT NULL,
    observaciones TEXT,
    estado VARCHAR(20),
    id_servicio INT NOT NULL,
    FOREIGN KEY (id_profesional) REFERENCES profesional(id_persona),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_persona),
    FOREIGN KEY (id_servicio) REFERENCES servicio(id_servicio)
);