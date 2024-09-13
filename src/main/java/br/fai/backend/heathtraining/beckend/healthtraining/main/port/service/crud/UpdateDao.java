package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud;

public interface UpdateDao<T> {
    void update(final int id, final T entity);
}
