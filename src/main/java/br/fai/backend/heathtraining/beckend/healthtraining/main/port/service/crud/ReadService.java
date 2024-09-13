package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud;

public interface ReadService<T> {
    T findById(final int id);
    List<T> findAll();
}
