CREATE TABLE fermentation (
    id_fermentation INT AUTO_INCREMENT PRIMARY KEY,
    codigo_tanque VARCHAR(10) NOT NULL,
    temperatura_actual INT NOT NULL
);

INSERT INTO fermentation (codigo_tanque, temperatura_actual) 
VALUES ('TK-A01', 18);

INSERT INTO fermentation (codigo_tanque, temperatura_actual) 
VALUES ('TK-B02', 12);
