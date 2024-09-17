package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;

import java.util.List;

public class GamePostgresDaoImpl implements GameDao {
    @Override
    public int add(GameModel entity) {
        return 0;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public GameModel readById(int id) {
        return null;
    }

    @Override
    public List<GameModel> readAll() {
        return List.of();
    }


}
