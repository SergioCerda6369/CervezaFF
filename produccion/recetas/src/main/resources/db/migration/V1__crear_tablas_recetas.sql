CREATE TABLE recetas (
    id_receta INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cerveza VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    ingredientes VARCHAR(255) NOT NULL,
    tiempo_coccion_minutos INT NOT NULL,
    dias_fermentacion INT NOT NULL
);

INSERT INTO recetas (nombre_cerveza, descripcion, ingredientes, tiempo_coccion_minutos, dias_fermentacion) 
VALUES ('Ipa Galáctica', 'Cerveza con intenso amargor y notas cítricas de lúpulos americanos.', 'Malta Pale, Lúpulo Cascade, Levadura Ale', 90, 14);