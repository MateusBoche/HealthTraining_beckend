package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;

public interface GetBestUserPoints {
    GameModel readyBestUserPoints(final GameModel gameModel);
}
