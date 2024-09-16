package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.fake;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question.QuestionDao;

import java.util.ArrayList;
import java.util.List;

public class QuestionFakeDaoImpl implements QuestionDao {
    private List<QuestionModel> questions = new ArrayList<>();
    private static int ID = 0;
    private int getNextId(){
        ID+=1;
        return ID;
    }
    public QuestionFakeDaoImpl(){
        System.out.println("instancia de question fake obitida");
        QuestionModel questionModel1 = new QuestionModel();
        questionModel1.setId(getNextId());
        questionModel1.setQuestion("questao1");
        questionModel1.setAnswer(true);
        questionModel1.setPhase(1);

        questions.add(questionModel1);
    }



    @Override
    public int add(QuestionModel entity) {
        final int id = getNextId();
        entity.setId(id);
        questions.add(entity);
        return id;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public QuestionModel readById(int id) {
        return null;
    }

    @Override
    public List<QuestionModel> readAll() {
        return null;
    }

    @Override
    public void updateInformation(int id, QuestionModel entity) {

    }

    @Override
    public QuestionModel readByCategory(String category) {
        return null;
    }
}
