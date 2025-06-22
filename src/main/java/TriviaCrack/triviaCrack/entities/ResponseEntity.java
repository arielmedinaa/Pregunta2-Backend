package TriviaCrack.triviaCrack.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseEntity <T> {
    private String status;
    private String message;
    private int totalRegister;
    private T data;
}
