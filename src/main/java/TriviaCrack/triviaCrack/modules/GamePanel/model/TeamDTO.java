package TriviaCrack.triviaCrack.modules.GamePanel.model;

import java.util.List;

public record TeamDTO(
         int id,
         int idUser,
         String name,
         int quantityParticipants,
         int quantityQuestions,
         int quantityPoints,

         List<UsuariosMod>members,
         boolean isActive,
         int currentQuestionIndex
) {
}
