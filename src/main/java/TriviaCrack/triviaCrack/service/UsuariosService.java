package TriviaCrack.triviaCrack.service;

import TriviaCrack.triviaCrack.config.ConfigConn;
import TriviaCrack.triviaCrack.modules.GamePanel.model.UsuariosMod;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Service
public class UsuariosService {

    // Este método deberá ser implementado con tu lógica JDBC específica
    // para buscar un usuario por su nombre en la base de datos.
    public Optional<UsuariosMod> findByName(String name) {
        System.out.println("ADVERTENCIA: UsuariosService.findByName no está implementado. Buscando usuario: " + name);
        // Lógica JDBC para buscar usuario por nombre
        // Ejemplo muy básico (DEBES ADAPTARLO):
        /*
        try (Connection conn = ConfigConn.getConexion();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id, name, password, is_admin, is_online, id_team, score, is_ready, has_answered FROM usuarios WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                UsuariosMod user = UsuariosMod.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .password(rs.getString("password"))
                        .isAdmin(rs.getBoolean("is_admin"))
                        .isOnline(rs.getBoolean("is_online"))
                        .idTeam(rs.getInt("id_team"))
                        .score(rs.getInt("score"))
                        .isReady(rs.getBoolean("is_ready"))
                        .hasAnswered(rs.getBoolean("has_answered"))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones adecuado
        }
        */
        return Optional.empty(); // Retornar Optional.empty() si no se encuentra
    }

    // Este método deberá ser implementado con tu lógica JDBC específica
    // para guardar un nuevo usuario en la base de datos o actualizar uno existente.
    public UsuariosMod save(UsuariosMod user) {
        System.out.println("ADVERTENCIA: UsuariosService.save no está implementado. Guardando usuario: " + user.getName());
        // Lógica JDBC para guardar o actualizar usuario
        // Ejemplo muy básico para inserción (DEBES ADAPTARLO):
        /*
        String sql = "INSERT INTO usuarios (name, password, is_admin, is_online, id_team, score, is_ready, has_answered) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConfigConn.getConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword()); // Asegúrate de que la contraseña esté hasheada antes de llegar aquí
            pstmt.setBoolean(3, user.isAdmin());
            pstmt.setBoolean(4, user.isOnline());
            pstmt.setInt(5, user.getIdTeam());
            pstmt.setInt(6, user.getScore());
            pstmt.setBoolean(7, user.isReady());
            pstmt.setBoolean(8, user.isHasAnswered());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    }
                }
            }
            return user; // Retornar el usuario con ID asignado si es una inserción
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones adecuado
        }
        */
        // Devuelve el usuario, idealmente con el ID si es una nueva inserción
        // o el usuario actualizado.
        return user;
    }
}
