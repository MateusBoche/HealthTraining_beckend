package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;

public interface AuthenticationService {
    UserModel authentication(final String email, final String senha);
}
