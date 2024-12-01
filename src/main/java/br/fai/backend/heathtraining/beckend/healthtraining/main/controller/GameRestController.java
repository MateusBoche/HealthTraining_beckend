package br.fai.backend.heathtraining.beckend.healthtraining.main.controller;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.GamePointsDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.ListarMelhoresDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game.GameService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.question.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
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

    @GetMapping("/listar-jogos/{email}")
    public ResponseEntity<List<GameModel>> getGamesForUserEmail(@PathVariable final String email) {
        System.out.println("Email recebido: " + email);
        List<GameModel> games = gameService.readGamesByEmail(email);
        System.out.println("Jogos encontrados: " + games.size());
        return ResponseEntity.ok().body(games);
    }


    @PostMapping
    public ResponseEntity<GameModel> createGame(@RequestBody final GameModel data) {
        int id = gameService.create(data);
        data.setId(id);
        return ResponseEntity.ok().body(data);
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

//    @PutMapping("/{id}/{userId}/{points}")
//    public ResponseEntity<Void> updateStatus(@PathVariable final int id, @PathVariable final int userId,@PathVariable final int points,) {
//        gameService.update(id,data);
//        return  ResponseEntity.ok().build();
//
//
//    }

    @PutMapping("/atualizar-status/{id}")
    public ResponseEntity<Boolean> updateStatus(@PathVariable final int id, @RequestBody final GameModel gameAtualizar) {
        try {
            // Chama o serviço para atualizar os pontos do jogo
            final boolean response = gameService.updatePoints(id, gameAtualizar);
            if (!response) {
                // Log de erro informativo
                System.out.println("Erro: pontos não foram atualizados para o jogo com ID " + id);
                return ResponseEntity.badRequest().body(false); // Retorna false no corpo em caso de erro
            }

            // Retorna sucesso com true no corpo
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao atualizar o jogo com ID " + id + ": " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }


    @GetMapping("/listar-melhores")
    public ResponseEntity<List<ListarMelhoresDto>>bestGames(){
        List<ListarMelhoresDto> games = gameService.readByBestUserPoints();
        return ResponseEntity.ok().body(games);


    }


}
