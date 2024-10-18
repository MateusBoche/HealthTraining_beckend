package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.GamePointsDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dto.ListarMelhoresDto;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GamePostgresDaoImpl implements GameDao {
    private static final Logger logger = Logger.getLogger(GamePostgresDaoImpl.class.getName());

    private final Connection connection;

    public GamePostgresDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(GameModel entity) {
        String sql = "INSERT INTO game(status, nivelAtual, usuarioId, numeroAcertos, numeroErros, dataDeCriacao, pontuacao)";
        sql += " VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getStatus());
            preparedStatement.setInt(2, entity.getNivelAtual());
            preparedStatement.setInt(3, entity.getUsuarioID());
            preparedStatement.setInt(4, entity.getNumeroAcertos());
            preparedStatement.setInt(5, entity.getNumeroErros());
            preparedStatement.setString(6, entity.getDataDeCriacao());
            preparedStatement.setInt(7, entity.getPontuacao());

            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                final int id = resultSet.getInt(1);
                entity.setId(id);
            }
            connection.commit();
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return entity.getId();
    }

    @Override
    public void remove(int id) {

        logger.log(Level.INFO, "Preparando para remover a entidade com id" + id);
        final String sql = "DELETE FROM game WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            logger.log(Level.INFO, "entidade removida com sucesso");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameModel readById(int id) {

        final String sql = "SELECT * FROM game WHERE id = ? ;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int entityId = resultSet.getInt("id");
                String status = resultSet.getString("status");
                String dataCriacao = resultSet.getString("datadecriacao");
                int nivelAtual = resultSet.getInt("nivelatual");
                int numeroErros = resultSet.getInt("numeroerros");
                int numeroAcertos = resultSet.getInt("numeroacertos");
                int usuarioId = resultSet.getInt("usuarioid");
                int pontuacao= resultSet.getInt("pontuacao");

                final GameModel game = new GameModel();
                game.setId(entityId);
                game.setStatus(status);
                game.setDataDeCriacao(dataCriacao);
                game.setNivelAtual(nivelAtual);
                game.setNumeroErros(numeroErros);
                game.setNumeroAcertos(numeroAcertos);
                game.setUsuarioID(usuarioId);
                game.setPontuacao(pontuacao);

                logger.log(Level.INFO, "entidade com id " + id + " encontrada");
                return game;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<GameModel> readGamesById(int id) {

        final List<GameModel> games = new ArrayList<>();
        final String sql = "SELECT * FROM game WHERE usuarioid = ? ;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int entityId = resultSet.getInt("id");
                String status = resultSet.getString("status");
                String dataCriacao = resultSet.getString("datadecriacao");
                int nivelAtual = resultSet.getInt("nivelatual");
                int numeroErros = resultSet.getInt("numeroerros");
                int numeroAcertos = resultSet.getInt("numeroacertos");
                int usuarioId = resultSet.getInt("usuarioid");
                int pontuacao = resultSet.getInt("pontuacao");

                final GameModel game = new GameModel();
                game.setId(entityId);
                game.setStatus(status);
                game.setDataDeCriacao(dataCriacao);
                game.setNivelAtual(nivelAtual);
                game.setNumeroErros(numeroErros);
                game.setNumeroAcertos(numeroAcertos);
                game.setUsuarioID(usuarioId);
                game.setPontuacao(pontuacao);

                games.add(game);

                logger.log(Level.INFO, "entidade com id " + id + " encontrada");

            }
            return games;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public List<GameModel> readAll() {

        final List<GameModel> games = new ArrayList<>();
        final String sql = "SELECT * FROM game";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                int entityId = resultSet.getInt("id");
                String status = resultSet.getString("status");
                String dataCriacao = resultSet.getString("datadecriacao");
                int nivelAtual = resultSet.getInt("nivelatual");
                int numeroErros = resultSet.getInt("numeroerros");
                int numeroAcertos = resultSet.getInt("numeroacertos");
                int usuarioId = resultSet.getInt("usuarioid");
                int pontuacao = resultSet.getInt("pontuacao");

                final GameModel game = new GameModel();
                game.setId(entityId);
                game.setStatus(status);
                game.setDataDeCriacao(dataCriacao);
                game.setNivelAtual(nivelAtual);
                game.setNumeroErros(numeroErros);
                game.setNumeroAcertos(numeroAcertos);
                game.setUsuarioID(usuarioId);
                game.setPontuacao(pontuacao);


                games.add(game);
            }

            resultSet.close();
            preparedStatement.close();
            return games;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updateInformation(int id, GameModel entity) {
        return;

    }

    @Override
    public boolean updatePoints(int id, GameModel gamePoints) {
        String sql = "UPDATE game SET numeroacertos = ?, numeroerros = ?, pontuacao = ?, nivelatual = ?";
        sql += " WHERE id = ? ";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, gamePoints.getNumeroAcertos());
            preparedStatement.setInt(2, gamePoints.getNumeroErros());
            preparedStatement.setInt(3, gamePoints.getPontuacao());
            preparedStatement.setInt(4, gamePoints.getNivelAtual());
            preparedStatement.setInt(5, gamePoints.getId());


            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            connection.commit();
            resultSet.close();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

    }



    @Override
    public List<ListarMelhoresDto> readByBestUserPoints() {
        final List<ListarMelhoresDto> bestPointGames = new ArrayList<>();
        String sql = "select UM.nomeCompleto,* from game G ";
        sql += " inner join user_model UM on UM.id = G.usuarioid ";
        sql += " ORDER BY G.pontuacao desc ";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final ListarMelhoresDto game = new ListarMelhoresDto();

                game.setNomeUsuario(resultSet.getString("nomeCompleto"));
                game.setId(resultSet.getInt("id"));
                game.setStatus(resultSet.getString("status"));
                game.setDataDeCriacao(resultSet.getString("datadecriacao"));
                game.setNivelAtual(resultSet.getInt("nivelatual"));
                game.setNumeroErros(resultSet.getInt("numeroerros"));
                game.setNumeroAcertos(resultSet.getInt("numeroacertos"));
                game.setUsuarioID(resultSet.getInt("usuarioid"));
                game.setPontuacao(resultSet.getInt("pontuacao"));

                bestPointGames.add(game);
            }

            resultSet.close();
            preparedStatement.close();
            return bestPointGames;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


