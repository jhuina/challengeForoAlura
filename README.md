# Foro API - Backend en Spring Boot

Este es un proyecto **backend de un Foro** desarrollado en **Java 17** con **Spring Boot**.  
El sistema permite el registro y autenticación de usuarios mediante **JWT**, además de la creación, edición, listado y eliminación lógica de tópicos (preguntas/discusiones) organizados por curso.

---

## 🚀 Tecnologías utilizadas
- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Flyway** (migraciones de base de datos)
- **Maven**

---

## ⚙️ Funcionalidades
- **Registro de usuarios**
- **Autenticación y Login** con JWT
- **Creación de tópicos** (asociados a un curso y un usuario autenticado)
- **Listado de tópicos** con:
  - Paginación (10 ítems por página)
  - Ordenados por fecha de creación
- **Edición de tópicos**
- **Eliminación lógica de tópicos** (campo `status = false`, no se borran físicamente)
- **Protección de endpoints** mediante autenticación con token JWT

---

## 📂 Estructura del proyecto
src/main/java/cl/foro/alura/huina/foro/
│── controller/ # Controladores REST
│── domain/
│ ├── curso/ # Entidad Curso + DTOs
│ ├── topico/ # Entidad Topico + DTOs
│ └── usuarios/ # Usuario, Autenticación y seguridad
│── infra/ # Configuración de seguridad (JWT, filters)

---

## 🛠️ Configuración del entorno

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/jhuina/challengeForoAlura.git
cd foro-api
