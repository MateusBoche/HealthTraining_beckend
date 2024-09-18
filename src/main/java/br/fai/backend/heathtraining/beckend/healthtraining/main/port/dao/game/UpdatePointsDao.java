package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game;

public interface UpdatePointsDao {
    boolean updatePoints(int gameId, int userId, int pointAcerto,int pointErro);
}
