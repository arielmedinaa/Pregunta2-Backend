package TriviaCrack.triviaCrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username; // o email
    private String password;
    private String firstname;
    private String lastname;
    // Agrega otros campos que necesites para el registro
}
