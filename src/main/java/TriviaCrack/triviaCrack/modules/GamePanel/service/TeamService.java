package TriviaCrack.triviaCrack.modules.GamePanel.service;

import TriviaCrack.triviaCrack.config.ConfigConn;
import TriviaCrack.triviaCrack.entities.ResponseEntity;
import TriviaCrack.triviaCrack.modules.GamePanel.model.TeamsMod;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final ConfigConn cone;

    public ResponseEntity<TeamsMod> createTeam(String nameTeam) {
        String sql = "INSERT INTO team (name) VALUES (?)";
        try (Connection conn = cone.getConnLocal();
             PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            try {
                ps.setString(1, nameTeam);
                ps.executeUpdate();

                return ResponseEntity.<TeamsMod>builder()
                        .data(TeamsMod.builder()
                                .name(nameTeam)
                                .build())
                        .message("EQUIPO CREADO EXITOSAMENTE.")
                        .build();
            } catch (Exception e) {
                Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR AL INTENTAR CREAR EL TEAM.");
            }
        } catch (SQLException e) {
            Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error de base de datos.");
        }
    }

    public List<TeamsMod> getAllTeams() {
        String sql = "SELECT * FROM team";
        List<TeamsMod> equipos = new ArrayList<>();

        try (Connection conn = cone.getConnLocal();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()
        ) {
            try{
                while (rs.next()) {
                    TeamsMod team = TeamsMod.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .quantityParticipants(rs.getInt("quantity_participants"))
                            .quantityQuestions(rs.getInt("quantity_questions"))
                            .quantityPoints(rs.getInt("quantity_points"))
                            .currentQuestionIndex(rs.getInt("current_question_index"))
                            .build();
                    equipos.add(team);
                }
                return equipos;
            }catch (Exception e){
                Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERROR AL INTENTAR OBTENER LOS EQUIPOS.");
            }
        } catch (SQLException e) {
            Logger.getLogger(TeamService.class.getName()).log(Level.SEVERE, null, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener los equipos.");
        }
    }


}
