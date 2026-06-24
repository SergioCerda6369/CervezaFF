CREATE TABLE stock_final (
    id_stock_final INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cerveza VARCHAR(100) NOT NULL,
    cantidad_disponible INT NOT NULL,
    precio_unitario DOUBLE NOT NULL
);

INSERT INTO stock_final (nombre_cerveza, cantidad_disponible, precio_unitario) 
VALUES ('Ipa Galáctica', 500, 3500.0);