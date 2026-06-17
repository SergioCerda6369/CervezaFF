CREATE TABLE recetas (
   idRecetas INT AUTO_INCREMENT PRIMARY KEY,
   nombreCerveza VARCHAR(100) NOT NULL,
   descripcion VARCHAR(255) NOT NULL,
   ingredientes VARCHAR(255) NOT NULL,
   tiempoCoccionMinutos INT NOT NULL,
   diasFermentacion INT NOT NULL

);