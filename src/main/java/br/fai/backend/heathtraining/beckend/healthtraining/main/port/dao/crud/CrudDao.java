package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud;

public interface CrudDao <T> extends CreateDao<T>, ReadDao<T>, UpdateDao<T>, DeleteDao {
}
