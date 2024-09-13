package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;

public interface ReadByEmailDao {
    UserModel readByEmail(final String email);
}