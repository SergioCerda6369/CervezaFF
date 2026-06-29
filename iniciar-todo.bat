@echo off
echo ============================================================
echo    INICIANDO ECOSISTEMA DE MICROSERVICIOS - CERVEZAFF
echo ============================================================

echo 1. Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)...
cd /d "%~dp0eureka"
start cmd /k "mvnw spring-boot:run"

echo Esperando 12 segundos a que Eureka se estabilice...
timeout /t 12 /nobreak > nul

echo 2. Iniciando API Gateway...
cd /d "%~dp0api-gateway"
start cmd /k "mvnw spring-boot:run"

echo 3. Iniciando Microservicio Calidades...
cd /d "%~dp0laboratorio\calidades\calidades"
start cmd /k "mvnw spring-boot:run"

echo 4. Iniciando Microservicio Fermentaciones...
cd /d "%~dp0laboratorio\fermentaciones\fermentaciones"
start cmd /k "mvnw spring-boot:run"

echo 5. Iniciando Microservicio Mantenimientos...
cd /d "%~dp0laboratorio\mantenimientos"
start cmd /k "mvnw spring-boot:run"

echo 6. Iniciando Microservicio Materiales...
cd /d "%~dp0produccion\materiales\materiales"
start cmd /k "mvnw spring-boot:run"

echo 7. Iniciando Microservicio Producciones...
cd /d "%~dp0produccion\producciones"
start cmd /k "mvnw spring-boot:run"

echo 8. Iniciando Microservicio Recetas...
cd /d "%~dp0produccion\recetas"
start cmd /k "mvnw spring-boot:run"

echo 9. Iniciando Microservicio Despachos...
cd /d "%~dp0ventas\despachos\despachos"
start cmd /k "mvnw spring-boot:run"

echo 10. Iniciando Microservicio Envasados...
cd /d "%~dp0ventas\envasados\envasados"
start cmd /k "mvnw spring-boot:run"

echo 11. Iniciando Microservicio Pedido...
cd /d "%~dp0ventas\pedido"
start cmd /k "mvnw spring-boot:run"

echo 12. Iniciando Microservicio Stocks...
cd /d "%~dp0ventas\stocks"
start cmd /k "mvnw spring-boot:run"

echo ============================================================
echo ¡Ecosistema lanzado! Dashboard disponible en http://localhost:8761
echo ============================================================