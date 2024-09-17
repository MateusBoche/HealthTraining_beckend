package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;

import java.sql.*;
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
        String sql = "INSERT INTO game(status, nivelatual, usuarioid, numeroacertos, numeroerros, datacriacao ";
        sql += " VALUES(?, ?, ?, ?, ?, ?);";
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

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameModel readById(int id) {




        return null;
    }

    @Override
    public List<GameModel> readAll() {
        return List.of();
    }


}
