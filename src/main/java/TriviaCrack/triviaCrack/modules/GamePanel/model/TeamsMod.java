package TriviaCrack.triviaCrack.modules.GamePanel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamsMod {
    private int id;
    private String name;
    private int quantityParticipants;
    private int quantityQuestions;
    private int quantityPoints;

    private List<UsuariosMod> members;
    private boolean isActive;
    private int currentQuestionIndex;
}
