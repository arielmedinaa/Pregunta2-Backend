# 🎯 Pregunta2 - Backend API

Este proyecto es el backend del juego **Pregunta2**, una aplicación de trivia multijugador inspirada en el clásico juego de preguntas y respuestas. Está construido con **Spring Boot** y utiliza **WebSockets** para simular partidas en tiempo real entre jugadores y equipos.

## 🚀 Características principales

- 🧠 Sistema de trivia con preguntas de diferentes categorías.
- 👥 Registro e inicio de sesión de usuarios.
- 🏆 Creación de equipos y gestión de jugadores.
- 🔄 Comunicación en tiempo real mediante WebSockets.
- 📈 Sistema de puntajes, historial de partidas y recompensas.
- 🛡️ Manejo de errores y respuestas personalizadas.

---

## ⚙️ Tecnologías utilizadas

<div align="left">

<a href="https://www.java.com" target="_blank">
  <img src="https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/>
</a>

<a href="https://spring.io/projects/spring-boot" target="_blank">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.0-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/>
</a>

<a href="https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket" target="_blank">
  <img src="https://img.shields.io/badge/Spring%20WebSocket-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring WebSocket"/>
</a>

<a href="https://spring.io/projects/spring-security" target="_blank">
  <img src="https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white" alt="Spring Security"/>
</a>

<a href="https://www.postgresql.org/" target="_blank">
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL"/>
</a>

<a href="https://projectlombok.org/" target="_blank">
  <img src="https://img.shields.io/badge/Lombok-FF8700?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok"/>
</a>

<a href="https://maven.apache.org/" target="_blank">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
</a>

</div>


---


---

## 🧪 Endpoints principales

| Método | Endpoint                  | Descripción                            |
|--------|---------------------------|----------------------------------------|
| POST   | `/trivia/auth/register`      | Registrar nuevo usuario                |
| POST   | `/trivia/auth/login`         | Iniciar sesión                         |
| POST   | `/trivia/team/create`        | Crear un nuevo equipo                  |
| GET    | `/trivia/team/{id}`          | Obtener detalles de un equipo          |
| WS     | `/ws/game`                   | Canal WebSocket para iniciar partida   |

---

## 💬 WebSocket Flow

1. Conexión inicial a `/ws/game`.
2. Envío de eventos como `JOIN_TEAM`, `ANSWER_QUESTION`, `NEXT_ROUND`.
3. Broadcast de actualizaciones a todos los miembros del equipo.


git clone https://github.com/tuusuario/preguntados-backend.git
cd preguntados-backend

