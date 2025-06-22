package TriviaCrack.triviaCrack.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RespuestasDTO {
    private String equipo;
    private String respuesta;
    private int ronda;
}
