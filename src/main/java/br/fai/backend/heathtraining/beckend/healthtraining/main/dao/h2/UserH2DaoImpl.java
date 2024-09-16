package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.h2;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserH2DaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserH2DaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        System.out.println("Ganhei uma instacvncia do userH2Dao");
    }

    @Override
    public int add(UserModel entity) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("user_model")
                .usingGeneratedKeyColumns("id");

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", entity.getEmail());
        parameters.put("senha",entity.getSenha());
        parameters.put("nomeCompleto",entity.getNomeCompleto());

        final Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        return id.intValue();
    }

    @Override
    public void remove(int id) {
        final String sql = "DELETE FROM user_model WHERE id = " + id;
        jdbcTemplate.execute(sql);

    }

    @Override
    public UserModel readById(int id) {

        final UserModel entity = jdbcTemplate
                .queryForObject("SELECT * FROM user_model WHERE id = ?",
                        new Object[]{id},
                        (rs, rowNum) -> new UserModel(
                                rs.getInt("id"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("nomeCompleto")
                        ));
        return entity;
    }

    @Override
    public List<UserModel> readAll() {
        final List<UserModel> entities = jdbcTemplate
                .query("SELECT * FROM user_model",
                        new Object[]{},
                        (rs, rowNum) -> new UserModel(
                                rs.getInt("id"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("nomeCompleto")
                        ));
        return entities;
    }

    @Override
    public void updateInformation(int id, UserModel entity) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE user_model SET ");
        stringBuilder.append(" nomeCompleto = ? ");
        stringBuilder.append(" WHERE id = ? ");
        jdbcTemplate.update(stringBuilder.toString(),
                entity.getNomeCompleto(), id);

    }

    @Override
    public UserModel readByEmail(String email) {
        final UserModel entity = jdbcTemplate
                .queryForObject("SELECT * FROM user_model WHERE email = ?",
                        new Object[]{email},
                        (rs, rowNum) -> new UserModel(
                                rs.getInt("id"),
                                rs.getString("email"),
                                rs.getString("senha"),
                                rs.getString("nomeCompleto")
                        ));
        return entity;
    }


    @Override
    public boolean updatePassword(int id, String newPassword) {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE user_model SET ");
        stringBuilder.append(" senha = ? ");
        stringBuilder.append(" WHERE id = ? ");
        final int updatedItems = jdbcTemplate.update(stringBuilder.toString(),
                newPassword, id);
        return updatedItems !=0;
    }
}

