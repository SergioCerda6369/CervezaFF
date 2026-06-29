CREATE TABLE packing (
    id_packing INT AUTO_INCREMENT PRIMARY KEY,
    tipo_envase VARCHAR(255) NOT NULL,
    cantidad_envases INT NOT NULL
);

INSERT INTO packing (tipo_envase, cantidad_envases)
VALUES ('Botella Vidrio 330cc', 1200);

INSERT INTO packing (tipo_envase, cantidad_envases)
VALUES ('Lata Aluminio 473cc', 2500);