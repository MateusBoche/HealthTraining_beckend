package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud;

public interface CreateDao <T>{
    int add(final T entity);
}
