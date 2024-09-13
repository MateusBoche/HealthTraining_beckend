package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud;

public interface createService<T> {
    int create(final T entity);
}
