package TriviaCrack.triviaCrack.modules.GamePanel.controller;

import TriviaCrack.triviaCrack.entities.ResponseEntity;
import TriviaCrack.triviaCrack.modules.GamePanel.model.TeamDTO;
import TriviaCrack.triviaCrack.modules.GamePanel.model.TeamsMod;
import TriviaCrack.triviaCrack.modules.GamePanel.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class TeamsController {

    private final TeamService teamsService;

    @PostMapping("/create")
    public ResponseEntity<TeamsMod> createTeam(@RequestBody TeamDTO data) {
        return teamsService.createTeam(data.name());
    }

}
