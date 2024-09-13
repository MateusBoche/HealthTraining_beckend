package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;

public interface ReadByEmailService {
    UserModel findByEmail(final String email);

}