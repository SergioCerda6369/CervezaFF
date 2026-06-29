CREATE TABLE mantenimiento (
    id_mantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    tipo_equipo VARCHAR(255) NOT NULL,
    codigo_equipo VARCHAR(10) NOT NULL,
    estado_equipo VARCHAR(255) NOT NULL,
    estado_mantenimiento VARCHAR(255) NOT NULL,
    idFermentation INT NOT NULL
);

INSERT INTO mantenimiento (tipo_equipo, codigo_equipo, estado_equipo, estado_mantenimiento, idFermentation) 
VALUES ('Intercambiador de Calor', 'HEX-01', 'Operativo', 'Completado', 1);

INSERT INTO mantenimiento (tipo_equipo, codigo_equipo, estado_equipo, estado_mantenimiento, idFermentation) 
VALUES ('Bomba de Transferencia', 'BOM-04', 'En Revisión', 'Pendiente', 2);