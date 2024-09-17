package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question.QuestionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class QuestionPostgresDaoImpl implements QuestionDao {

    private static final Logger logger = Logger.getLogger(QuestionPostgresDaoImpl.class.getName());

    private final Connection connection;

    public QuestionPostgresDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(QuestionModel entity) {
        String sql = "INSERT INTO question_model(question, answer, category, phase) ";
        sql+= " VALUES(?, ?, ?);";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try{
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,entity.getQuestion());
            preparedStatement.setBoolean(2, entity.isAnswer());
            preparedStatement.setString(3, entity.getCategory());
            preparedStatement.setInt(4, entity.getPhase());

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                final int id = resultSet.getInt(1);
                entity.setId(id);
            }
            connection.commit();

            resultSet.close();
            preparedStatement.close();
        }catch (SQLException e){
            try {
                connection.rollback();
            }catch (SQLException ex){
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return entity.getId();
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public QuestionModel readById(int id) {
        return null;
    }

    @Override
    public List<QuestionModel> readAll() {
        return null;
    }

    @Override
    public void updateInformation(int id, QuestionModel entity) {

    }

    @Override
    public QuestionModel readByCategory(String category) {
        return null;
    }
}
