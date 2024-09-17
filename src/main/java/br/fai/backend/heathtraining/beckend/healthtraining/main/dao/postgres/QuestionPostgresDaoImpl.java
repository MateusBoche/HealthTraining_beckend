package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question.QuestionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
        logger.log(Level.INFO, "Preparando para remover a questao com o id " + id);
        final String sql = " DELETE FROM question_model WHERE id = ? ;";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            logger.log(Level.INFO, "Entidade removida com sucesso");
            preparedStatement.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public QuestionModel readById(int id) {
        final String sql = "SELECT * FROM question_model WHERE id = ? ;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection
                    .prepareStatement(sql);
            preparedStatement.setInt(1,id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                final QuestionModel question = new QuestionModel();

                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("question"));
                question.setAnswer(resultSet.getBoolean("aswer"));
                question.setCategory(resultSet.getString("category"));
                question.setPhase(resultSet.getInt("phase"));

                logger.log(Level.INFO, "questao com id " + id + "encontrada");
                return question;
            }
            return null;

        }catch(Exception e){
            throw new RuntimeException(e);
        } finally{
            try {
                resultSet.close();
                preparedStatement.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public List<QuestionModel> readAll() {
        final List<QuestionModel> questions = new ArrayList<>();
        final String sql = "SELECT * FROM question_model;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                final QuestionModel question = new QuestionModel();

                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("question"));
                question.setAnswer(resultSet.getBoolean("aswer"));
                question.setCategory(resultSet.getString("category"));
                question.setPhase(resultSet.getInt("phase"));

                questions.add(question);
            }
            resultSet.close();
            preparedStatement.close();
            return questions;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateInformation(int id, QuestionModel entity) {

    }

    @Override
    public QuestionModel readByCategory(String category) {
        return null;
    }
}
