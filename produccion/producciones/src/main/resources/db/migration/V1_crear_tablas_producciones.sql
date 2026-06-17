CREATE TABLE producciones (
   idProduccion INT AUTO_INCREMENT PRIMARY KEY,
   idReceta INT NOT NULL,
   codigoLote VARCHAR(50) NOT NULL,
   cantidadLitros INT NOT NULL,
   fechaInicio VARCHAR(255) NOT NULL,
   estadoProduccion VARCHAR(50) NOT NULL  

);