package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud;

import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.CreateDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.DeleteDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.ReadDao;

public interface CrudDao <T> extends CreateDao<T>, ReadDao<T>, UpdateDao<T>, DeleteDao

{
}