CREATE TABLE producciones (
    id_produccion INT AUTO_INCREMENT PRIMARY KEY,
    id_receta INT NOT NULL,
    codigo_lote VARCHAR(50) NOT NULL,
    cantidad_litros INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    estado_produccion VARCHAR(50) NOT NULL
);

INSERT INTO producciones (id_receta, codigo_lote, cantidad_litros, fecha_inicio, estado_produccion)
VALUES (1, 'LOTE-IPA-001', 500, '2026-06-28', 'En Progreso');