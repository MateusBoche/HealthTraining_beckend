package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user;

public interface UpdatePasswordDao {
    boolean updatePassword(final int id, final String newPassword);
}
