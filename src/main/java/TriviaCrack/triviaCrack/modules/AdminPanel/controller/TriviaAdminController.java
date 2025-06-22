package TriviaCrack.triviaCrack.modules.AdminPanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class TriviaAdminController {

    private final SimpMessagingTemplate messagingTemplate;
}
