package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.GamePointsDto;

public interface UpdateGamePointsService {

    boolean updatePoints(int gameId, GameModel gameAtualizar);
}
