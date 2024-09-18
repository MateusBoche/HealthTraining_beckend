package br.fai.backend.heathtraining.beckend.healthtraining.main.controller;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game.GameService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.question.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/game")
public class GameRestController {
    private final GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<GameModel>> getAllGames() {
        List<GameModel> games = gameService.findAll();
        return ResponseEntity.ok().body(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameModel> getGameById(@PathVariable final int id) {
        GameModel game = gameService.findById(id);
        return  ResponseEntity.ok().body(game);
    }

    @PostMapping
    public ResponseEntity<GameModel> createGame(@RequestBody final GameModel data) {
        int id = gameService.create(data);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGame(@PathVariable final int id, @RequestBody final GameModel data) {
        gameService.update(id,data);
        return  ResponseEntity.ok().build();


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable final int id) {
          gameService.delete(id);
          return  ResponseEntity.noContent().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateStatus(@PathVariable final int id, @RequestBody final GameModel data) {
//        gameService.update(id,data);
//        return  ResponseEntity.ok().build();
//
//
//    }



}
