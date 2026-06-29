#  CervezaFF

## Descripción

CervezaFF es un proyecto desarrollado como parte de una actividad académica de la carrera. Su objetivo es administrar la información de una cervecería mediante un sistema que permite gestionar los productos y facilitar su administración.

Con este proyecto se busca aplicar los conocimientos adquiridos sobre desarrollo de aplicaciones utilizando Java y Spring Boot, además del manejo de bases de datos y el trabajo con una arquitectura organizada.

---

## Objetivos

- Aplicar programación orientada a objetos.
- Desarrollar una aplicación utilizando Spring Boot.
- Conectar la aplicación con una base de datos.
- Implementar operaciones CRUD.
- Aplicar buenas prácticas básicas de programación.

---

## Funcionalidades

El sistema permite:

- Registrar nuevos productos.
- Consultar los productos registrados.
- Modificar la información de un producto.
- Eliminar productos.
- Almacenar la información en una base de datos.

---

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Maven
- MySQL
- HTML
- CSS
- Bootstrap
- Git
- GitHub

---

## Estructura del proyecto

```
CervezaFF
│
├── src
│   ├── main
│   │   ├── java
│   │   ├── resources
│   │   └── templates
│   └── test
│
├── pom.xml
└── README.md
```

---

## Requisitos

Para ejecutar el proyecto es necesario tener instalado:

- Java JDK 17 o superior.
- Maven.
- MySQL.
- Un IDE como IntelliJ IDEA o Eclipse.

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/SergioCerda6369/CervezaFF.git
```

### 2. Entrar a la carpeta del proyecto

```bash
cd CervezaFF
```

### 3. Configurar la base de datos

Crear una base de datos en MySQL y modificar el archivo:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cervezaff
spring.datasource.username=root
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
```

### 4. Ejecutar el proyecto

Desde el IDE ejecutar la clase principal o utilizar el siguiente comando:

```bash
mvn spring-boot:run
```

---

## Aprendizajes

Durante el desarrollo de este proyecto se reforzaron conocimientos sobre:

- Programación orientada a objetos.
- Desarrollo con Spring Boot.
- Uso de repositorios con Spring Data JPA.
- Conexión con bases de datos MySQL.
- Organización de un proyecto por capas.
- Uso de Git y GitHub para el control de versiones.

---

## Autores

**Sergio Cerda**
**Martin Silva**

Proyecto desarrollado con fines académicos.
