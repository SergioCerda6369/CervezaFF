CREATE TABLE pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL
);

INSERT INTO pedidos (cliente, cantidad) VALUES ('Bar Central', 50);