package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud;

public interface CreateService<T> {
    int create(final T entity);
}
