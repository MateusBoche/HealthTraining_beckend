package br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;

public interface ReadByCategoryDao {
  QuestionModel readByCategory(final String category);
}
