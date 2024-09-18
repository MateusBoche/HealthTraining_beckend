package br.fai.backend.heathtraining.beckend.healthtraining.main.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game.GameService;
import org.springframework.stereotype.Service;

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
        if (game == null){
            return;
        }
        gameDao.updateInformation(id, entity);
    }

    @Override
    public boolean updatePoints(int gameId, int userId, int points) {
        return false;
    }
}
