package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;

public interface GetBestUserPoints {
    GameModel readByBestUserPoints(GameModel gameModel);
}

