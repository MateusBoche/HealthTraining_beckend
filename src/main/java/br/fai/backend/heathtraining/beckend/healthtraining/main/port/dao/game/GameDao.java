package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.CreateDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.CrudDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.DeleteDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.ReadDao;

public interface GameDao extends CreateDao<GameModel>, DeleteDao, ReadDao<GameModel> {
}
