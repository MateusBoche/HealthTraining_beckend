package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPostgresDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserPostgresDaoImpl.class.getName());

    private final Connection connection;

    public UserPostgresDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(UserModel entity){
        String sql = "INSERT INTO user_model(nomeCompleto, email, senha) ";
        sql+= " VALUES(?, ?, ?);";

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {

            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getSenha());
            preparedStatement.setString(2, entity.getNomeCompleto());
            preparedStatement.setString(3, entity.getEmail());

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
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
        final String sql = "DELETE FROM user_model WHERE id = ? ;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            preparedStatement.close();
            logger.log(Level.INFO,"Entidade removida com sucesso");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public UserModel readByEmail(String email) {


        final String sql = "SELECT * FROM user_model WHERE email = ? ;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                final UserModel user = new UserModel();


                user.setId(resultSet.getInt("id"));
                user.setNomeCompleto(resultSet.getString("nomeCompleto"));
                user.setEmail(resultSet.getString("email"));
                user.setSenha(resultSet.getString("senha"));

                logger.log(Level.INFO, "entidade com email" + email + " encontrada");
                return user;
            }
            return null;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        String sql = "UPDATE user_model SET senha = ? ";
        sql += " WHERE id = ? ";

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }




    @Override
    public UserModel readById(int id) {

        final String sql = "SELECT * FROM user_model WHERE id = ? ;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                final UserModel user = new UserModel();


                user.setId(resultSet.getInt("id"));
                user.setNomeCompleto(resultSet.getString("nomeCompleto"));
                user.setEmail(resultSet.getString("email"));
                user.setSenha(resultSet.getString("senha"));

                logger.log(Level.INFO, "entidade com id" + id + " encontrada");
                return user;
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
    public List<UserModel> readAll() {

        final List<UserModel> users = new ArrayList<>();
        final String sql = "SELECT * FROM user_model";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int entityId = resultSet.getInt("id");
                String nomeCompleto = resultSet.getString("nomeCompleto");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");

                final UserModel user = new UserModel();
                user.setId(entityId);
                user.setEmail(email);
                user.setNomeCompleto(nomeCompleto);
                user.setSenha(senha);

                users.add(user);
            }
            resultSet.close();
            preparedStatement.close();
            return users;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void updateInformation(int id, UserModel entity) {


        String sql = "UPDATE user_model SET nomeCompleto = ? ";
        sql += " WHERE id = ? ";

        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, entity.getNomeCompleto());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
