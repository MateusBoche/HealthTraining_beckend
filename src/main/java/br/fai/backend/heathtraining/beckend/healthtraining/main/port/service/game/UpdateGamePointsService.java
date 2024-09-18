package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game;

public interface UpdateGamePointsService {

    boolean updatePoints(int gameId, int userId, int points, int pointErro);
}
