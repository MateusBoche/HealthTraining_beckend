package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user;

public interface RecoveryPasswordService {
    boolean recoveryPassword(final int id, final String newPassword);
}
