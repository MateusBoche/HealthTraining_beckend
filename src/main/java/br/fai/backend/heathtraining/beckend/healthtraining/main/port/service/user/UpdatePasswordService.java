package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user;

public interface UpdatePasswordService {
    boolean updatePassword(final int id, final String oldPassword, final String newPassword);
}
