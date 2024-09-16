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
        int itemIndex = -1;
        for(int i = 0; i< questions.size();i++){
            QuestionModel question = questions.get(i);
            if(question.getId()==id){
                itemIndex =1;
                break;
            }
        }
        if(itemIndex ==-1){
            return;
        }
        QuestionModel removedEntity = questions.remove(itemIndex);
        System.out.println("A pergunta " + removedEntity.getQuestion() + "foi removida. Id da pergunta removida " + removedEntity.getId());

    }

    @Override
    public QuestionModel readById(int id) {
        for(QuestionModel question:questions){
            if(question.getId()==id){
                return question;
            }
        }
        return null;
    }

    @Override
    public List<QuestionModel> readAll() {
        return questions;
    }

    @Override
    public void updateInformation(int id, QuestionModel entity) {
        QuestionModel question = readById(id);
        question.setQuestion(entity.getQuestion());
    }

    @Override
    public QuestionModel readByCategory(String category) {
        return null;
    }
}
