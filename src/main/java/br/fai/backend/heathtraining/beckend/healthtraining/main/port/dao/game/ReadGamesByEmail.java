package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;

import java.util.List;

public interface ReadGamesByEmail {
  List<GameModel> readGamesById(String email);
}
