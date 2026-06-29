CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    cliente VARCHAR(100) NOT NULL,
    id_stock_final INT NOT NULL,
    cantidad_solicitada INT NOT NULL,
    total_venta DOUBLE NOT NULL,
    estado_pedido VARCHAR(50) NOT NULL
);

INSERT INTO pedidos (cliente, id_stock_final, cantidad_solicitada, total_venta, estado_pedido) 
VALUES ('Bar Central', 1, 50, 175000.0, 'Pendiente');