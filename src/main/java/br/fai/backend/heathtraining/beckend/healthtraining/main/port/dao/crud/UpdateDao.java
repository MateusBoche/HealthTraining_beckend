package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud;

public interface UpdateDao<T> {
    void updateInformation(final int id, final T entity);
}
