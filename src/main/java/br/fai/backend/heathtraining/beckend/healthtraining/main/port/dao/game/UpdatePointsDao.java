package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.GamePointsDto;

public interface UpdatePointsDao {
    boolean updatePoints(int gameId, GameModel gamePointsDto);
}
