package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.crud.CrudDao;

public interface QuestionDao extends CrudDao<QuestionModel>, ReadByCategoryDao {
}
