package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.question;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;

public interface ReadByCategoryService {
  QuestionModel findByCategory(final String category);
}
