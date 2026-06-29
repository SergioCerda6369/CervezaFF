#!/bin/bash

echo "============================================================"
echo "   INICIANDO ECOSISTEMA DE MICROSERVICIOS - CERVEZAFF (Mac)"
echo "============================================================"

BASE_DIR=$(pwd)

echo "1. Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/eureka' && ./mvnw spring-boot:run\""

echo "Esperando 12 segundos a que Eureka se estabilice..."
sleep 12

echo "2. Iniciando API Gateway..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/api-gateway' && ./mvnw spring-boot:run\""

echo "3. Iniciando Microservicio Calidades..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/laboratorio/calidades/calidades' && ./mvnw spring-boot:run\""

echo "4. Iniciando Microservicio Fermentaciones..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/laboratorio/fermentaciones/fermentaciones' && ./mvnw spring-boot:run\""

echo "5. Iniciando Microservicio Mantenimientos..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/laboratorio/mantenimientos' && ./mvnw spring-boot:run\""

echo "6. Iniciando Microservicio Materiales..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/produccion/materiales/materiales' && ./mvnw spring-boot:run\""

echo "7. Iniciando Microservicio Producciones..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/produccion/producciones' && ./mvnw spring-boot:run\""

echo "8. Iniciando Microservicio Recetas..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/produccion/recetas' && ./mvnw spring-boot:run\""

echo "9. Iniciando Microservicio Despachos..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/ventas/despachos/despachos' && ./mvnw spring-boot:run\""

echo "10. Iniciando Microservicio Envasados..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/ventas/envasados/envasados' && ./mvnw spring-boot:run\""

echo "11. Iniciando Microservicio Pedido..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/ventas/pedido' && ./mvnw spring-boot:run\""

echo "12. Iniciando Microservicio Stocks..."
osascript -e "tell application \"Terminal\" to do script \"cd '$BASE_DIR/ventas/stocks' && ./mvnw spring-boot:run\""

echo "============================================================"
echo "Ecosistema lanzado. Dashboard disponible en http://localhost:8761"
echo "============================================================"