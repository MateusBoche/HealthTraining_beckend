package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setString(2, entity.getFullName());
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
    public UserModel readByEmail(String email) {
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        return false;
    }
}
