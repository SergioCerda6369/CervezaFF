CREATE TABLE dispatch (
    id_dispatch INT AUTO_INCREMENT PRIMARY KEY,
    patente_camion VARCHAR(6) NOT NULL,
    nombre_conductor VARCHAR(255) NOT NULL,
    estado_dispatch VARCHAR(100) NOT NULL,
    id_pedido INT NOT NULL
);

INSERT INTO dispatch (patente_camion, nombre_conductor, estado_dispatch, id_pedido) 
VALUES ('BBCC11', 'Juan Pérez', 'En Ruta', 1);

INSERT INTO dispatch (patente_camion, nombre_conductor, estado_dispatch, id_pedido) 
VALUES ('KKLW45', 'Carlos Muñoz', 'Preparado', 2);