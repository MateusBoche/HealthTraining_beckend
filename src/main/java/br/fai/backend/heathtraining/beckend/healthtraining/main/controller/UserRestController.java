package br.fai.backend.heathtraining.beckend.healthtraining.main.controller;

import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
}
