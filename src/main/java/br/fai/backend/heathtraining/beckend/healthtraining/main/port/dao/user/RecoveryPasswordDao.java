package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user;

public interface RecoveryPasswordDao {
    boolean recoveryPassword(final int id, final String newPassword);
}
