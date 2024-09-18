package br.fai.backend.heathtraining.beckend.healthtraining.main.service.question;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.UserModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question.QuestionDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.game.GameService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.question.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

  private final QuestionDao questionDao;

  public QuestionServiceImpl(QuestionDao questionDao) {
    this.questionDao = questionDao;
  }


  @Override
  public void delete(int id) {
    if(id<0){
      return;
    }
    questionDao.remove(id);

  }

  @Override
  public QuestionModel findById(int id) {
    if(id<0){
      return null;
    }
    QuestionModel question = questionDao.readById(id);
    return question;

  }

  @Override
  public List<QuestionModel> findAll() {
    List<QuestionModel> questionModels = questionDao.readAll();
    return questionModels;
  }


  @Override
  public int create(QuestionModel entity) {
    if (entity == null) {
      return 0;
    }
    if (entity.getQuestion().isEmpty()
            || entity.getCategory().isEmpty()){
      return 0;

    }
    int id = questionDao.add(entity);
    return id;
  }

  @Override
  public void update(int id, QuestionModel entity) {
    QuestionModel question = findById(id);
    if(question == null){
      return;
    }
    questionDao.updateInformation(id,entity);

  }

  public QuestionModel findByCategory(String category) {
    if (category.isEmpty()) {
      return null;
    }
    return questionDao.readByCategory(category);
  }


}

