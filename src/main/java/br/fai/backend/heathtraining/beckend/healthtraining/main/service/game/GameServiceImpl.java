package br.fai.backend.heathtraining.beckend.healthtraining.main.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game.GameService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GameServiceImpl implements GameService {

  private final GameDao gameDao;

  public GameServiceImpl(GameDao gameDao) {
    this.gameDao = gameDao;
  }


  @Override
  public int create(GameModel entity) {
    if (entity == null) {
      return 0;
    }

    if (entity.getStatus() == null ||
      entity.getStatus().isEmpty()) {
      return 0;
    }

    int id = gameDao.add(entity);
    return id;
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
    if (id < 0) {
      return null;
    }
    GameModel game = gameDao.readById(id);
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
  public boolean updatePoints(int gameId, int userId, int point, int pointErro) {
    return false;
  }


  @Override
  public List<GameModel> readByBestUserPoints() {
    List<GameModel> bestPoint = new ArrayList<>();
    System.out.println("find all foi chamado");
    List<GameModel> games = gameDao.readByBestUserPoints();
//    GameModel best = games.get(0);
//    for (GameModel game : games) {
//      if(best.getNumeroAcertos()<=game.getNumeroAcertos()){
//        best = game;
//      }
//
//    }
//    bestPoint.add(best);
//    games.remove(best);
//    GameModel best2 = games.get(0);
//    for (GameModel game : games) {
//      if(best2.getNumeroAcertos()<=game.getNumeroAcertos()){
//        best2 = game;
//      }
//
//    }
//    bestPoint.add(best2);
//    games.remove(best2);
//
//    GameModel best3 = games.get(0);
//    for (GameModel game : games) {
//      if(best3.getNumeroAcertos()<=game.getNumeroAcertos()){
//        best3 = game;
//      }
//
//    }
//    bestPoint.add(best3);
//    games.remove(best3);

    return games;
  }

  @Override
  public List<GameModel> readGamesById(int id) {
    if (id < 0) {
      return null;
    }

//    List<GameModel> todos = gameDao.readAll();
    List<GameModel> games = gameDao.readGamesById(id);

    return games;
  }
}

