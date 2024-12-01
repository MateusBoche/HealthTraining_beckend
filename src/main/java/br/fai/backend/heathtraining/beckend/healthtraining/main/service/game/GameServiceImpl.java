package br.fai.backend.heathtraining.beckend.healthtraining.main.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.GamePointsDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.ListarMelhoresDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class GameServiceImpl implements GameService {

  private final GameDao gameDao;

  public GameServiceImpl(GameDao gameDao) {
    this.gameDao = gameDao;
  }


  @Override
  public int create(GameModel entity) {
    try {
      if (entity == null) {
        throw new IllegalArgumentException("O objeto GameModel não pode ser nulo.");
      }
      if (entity.getStatus() == null || entity.getStatus().isEmpty()) {
        throw new IllegalArgumentException("O campo 'status' é obrigatório.");
      }
      if (entity.getUsuarioID() <= 0) {
        throw new IllegalArgumentException("O campo 'usuarioID' deve ser válido.");
      }
      int id = gameDao.add(entity);
      // Verifica se o ID retornado é válido
      if (id <= 0) {
        throw new IllegalStateException("Erro ao criar o jogo no banco de dados. ID inválido retornado.");
      }
      return id;
    } catch (IllegalArgumentException e) {
      System.err.println("Erro de validação ao criar o jogo: " + e.getMessage());
      return 0; // Retorna 0 em caso de erro de validação
    } catch (Exception e) {
      System.err.println("Erro inesperado ao criar o jogo: " + e.getMessage());
      return 0; // Retorna 0 em caso de erro geral
    }
  }


  @Override
  public void delete(int id) {
    if (id < 0) {
      return;
    }
    gameDao.remove(id);
  }

  @Override
  public GameModel findById(int id) {
    System.out.println("Buscando jogo com ID: " + id);
    GameModel game = gameDao.readById(id);
    if (game == null) {
      System.out.println("Jogo não encontrado para o ID: " + id);
    }
    return game;
  }


  @Override
  public List<GameModel> findAll() {
    System.out.println("find all foi chamado");
    List<GameModel> games = gameDao.readAll();
    return games;
  }

  @Override
  public void update(int id, GameModel entity) {
    GameModel game = findById(id);
    if (game == null) {
      return;
    }
    gameDao.updateInformation(id, entity);
  }

  @Override
  public boolean updatePoints(int gameId, GameModel gameatualizar) {
    if (gameatualizar == null) {
      return false;
    }
    boolean responseT = gameDao.updatePoints(gameId, gameatualizar);


    return responseT;
  }


  @Override
  public List<ListarMelhoresDto> readByBestUserPoints() {
    List<GameModel> bestPoint = new ArrayList<>();
    System.out.println("find all foi chamado");
    List<ListarMelhoresDto> games = gameDao.readByBestUserPoints();

    return games;
  }

  @Override
  public List<GameModel> readGamesByEmail(String email) {
    List<GameModel> games = new ArrayList<>();
    games = gameDao.readGamesByEmail(email);
    return games;
  }






}

