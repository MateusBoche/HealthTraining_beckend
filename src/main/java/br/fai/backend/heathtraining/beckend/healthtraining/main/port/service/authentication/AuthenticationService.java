package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication;

public interface AuthenticationService {
    void authenticate(final String email, final String senha);
}
