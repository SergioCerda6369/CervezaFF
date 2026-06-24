@echo off

echo Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)...
cd eureka
start cmd /k "mvnw spring-boot:run"

echo Esperando 12 segundos a que Eureka se estabilice...
timeout /t 12 /nobreak > null

echo Iniciando API Gateway...
cd ../api-gateway
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Calidades...
cd ../laboratorio/calidades
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Fermentaciones...
cd ../fermentaciones
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Mantenimientos...
cd ../mantenimientos
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Materiales...
cd ../../produccion/materiales
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Producciones...
cd ../producciones
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Recetas...
cd ../recetas
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Despachos...
cd ../../ventas/despachos
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Envasados...
cd ../envasados
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Pedido...
cd ../pedido
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Stocks...
cd ../stocks
start cmd /k "mvnw spring-boot:run"

echo Ecosistema lanzado. Dashboard disponible en http://localhost:8761