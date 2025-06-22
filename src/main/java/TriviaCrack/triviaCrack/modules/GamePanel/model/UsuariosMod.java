package TriviaCrack.triviaCrack.modules.GamePanel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuariosMod {
    private int id;
    private String name;
    private boolean isAdmin;
    private boolean isOnline;
    private int idTeam;

    private int score;
    private boolean isReady;
    private boolean hasAnswered;
}
