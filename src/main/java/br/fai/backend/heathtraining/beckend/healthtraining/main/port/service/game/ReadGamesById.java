package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;

import java.util.List;

public interface ReadGamesById {
  List<GameModel> readGamesById(final int id);
}
