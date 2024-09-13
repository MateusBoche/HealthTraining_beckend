package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud;

public interface UpdateService<T> {
    void update(final int id, final T entity);
}
