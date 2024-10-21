package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.CrudDao;

public interface UserDao extends CrudDao<UserModel>,ReadByEmailDao, UpdatePasswordDao, RecoveryPasswordDao {
}
