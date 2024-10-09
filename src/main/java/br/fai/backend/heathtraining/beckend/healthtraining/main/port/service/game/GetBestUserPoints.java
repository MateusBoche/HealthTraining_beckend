package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;

import java.util.List;

public interface GetBestUserPoints {
    List<GameModel> readyBestUserPoints();
}
