package br.fai.backend.heathtraining.beckend.healthtraining.main.question;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.QuestionModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question.QuestionDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.service.question.QuestionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class QuestionServiceImplTest {

    // Mock do DAO usado para simular interações com a camada de dados
    @Mock
    private QuestionDao questionDao;

    // Instância do serviço que será testada, com dependências injetadas pelo Mockito
    @InjectMocks
    private QuestionServiceImpl questionService;

    // Configura o ambiente de mocks antes de cada teste
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


      // Teste para verificar o comportamento do método findAll em um cenário de sucesso.

    @Test
    public void testFindAll() {
        // Configura uma lista de questões simuladas
        List<QuestionModel> questions = new ArrayList<>();
        QuestionModel question = new QuestionModel();
        question.setId(1);
        question.setQuestion("O que é cicatrização?");
        question.setCategory("Saúde");
        questions.add(question);

        // Simula o retorno do método readAll no DAO
        when(questionDao.readAll()).thenReturn(questions);

        // Chama o método findAll do serviço
        List<QuestionModel> result = questionService.findAll();

        // Valida o resultado
        assertNotNull(result); // Verifica que o resultado não é nulo
        assertEquals(1, result.size()); // Verifica que o tamanho da lista é 1
        verify(questionDao, times(1)).readAll(); // Garante que o método do DAO foi chamado
    }


     // Teste para verificar o comportamento do método findById em um cenário de sucesso.

    @Test
    public void testFindById() {
        // Configura uma questão simulada
        QuestionModel question = new QuestionModel();
        question.setId(1);
        question.setQuestion("O que é cicatrização?");
        question.setCategory("Saúde");

        // Simula o retorno do método readById no DAO
        when(questionDao.readById(1)).thenReturn(question);

        // Chama o método findById do serviço
        QuestionModel result = questionService.findById(1);

        // Valida o resultado
        assertNotNull(result); // Verifica que o resultado não é nulo
        assertEquals("O que é cicatrização?", result.getQuestion()); // Compara o texto da questão
        verify(questionDao, times(1)).readById(1); // Garante que o método do DAO foi chamado
    }



    //Teste para verificar o comportamento do método create em um cenário de sucesso.

    @Test
    public void testCreate() {
        // Configura uma nova questão
        QuestionModel question = new QuestionModel();
        question.setQuestion("O que é necrose?");
        question.setCategory("Feridas");

        // Simula o retorno do método add no DAO
        when(questionDao.add(question)).thenReturn(1);

        // Chama o método create do serviço
        int result = questionService.create(question);

        // Valida o resultado
        assertEquals(1, result); // Verifica que o ID retornado é 1
        verify(questionDao, times(1)).add(question); // Garante que o método do DAO foi chamado
    }


     //Teste para verificar o comportamento do método update em um cenário de sucesso.

    @Test
    public void testUpdate() {
        // Configura uma questão existente e uma versão atualizada
        QuestionModel existingQuestion = new QuestionModel();
        existingQuestion.setId(1);
        existingQuestion.setQuestion("O que é necrose?");
        existingQuestion.setCategory("Feridas");

        QuestionModel updatedQuestion = new QuestionModel();
        updatedQuestion.setQuestion("O que é tecido de granulação?");
        updatedQuestion.setCategory("Feridas");

        // Simula o retorno do método readById no DAO
        when(questionDao.readById(1)).thenReturn(existingQuestion);

        // Chama o método update do serviço
        questionService.update(1, updatedQuestion);

        // Garante que o método updateInformation foi chamado corretamente
        verify(questionDao, times(1)).updateInformation(1, updatedQuestion);
    }


     // Teste para verificar o comportamento do método delete em um cenário de sucesso.

    @Test
    public void testDelete() {
        // Chama o método delete do serviço
        questionService.delete(1);

        // Garante que o método remove do DAO foi chamado
        verify(questionDao, times(1)).remove(1);
    }


     // Teste para verificar o comportamento do método findByCategory em um cenário de sucesso.

    @Test
    public void testFindByCategory() {
        // Configura uma questão simulada
        QuestionModel question = new QuestionModel();
        question.setId(1);
        question.setQuestion("Como limpar uma ferida?");
        question.setCategory("Saúde");

        // Simula o retorno do método readByCategory no DAO
        when(questionDao.readByCategory("Saúde")).thenReturn(question);

        // Chama o método findByCategory do serviço
        QuestionModel result = questionService.findByCategory("Saúde");

        // Valida o resultado
        assertNotNull(result); // Verifica que o resultado não é nulo
        assertEquals("Saúde", result.getCategory()); // Compara a categoria
        verify(questionDao, times(1)).readByCategory("Saúde"); // Garante que o método do DAO foi chamado
    }

    // Os métodos abaixo seguem a mesma lógica de validação, mas para cenários de falha.

    @Test
    public void testFindAllFail() {
        when(questionDao.readAll()).thenReturn(null);

        List<QuestionModel> result = questionService.findAll();

        assertNull(result, "A lista deveria ser nula em caso de falha.");
        verify(questionDao, times(1)).readAll();
    }

    @Test
    public void testFindByIdFail() {
        when(questionDao.readById(999)).thenReturn(null);

        QuestionModel result = questionService.findById(999);

        assertNull(result, "Nenhuma questão deveria ser encontrada para o ID inexistente.");
        verify(questionDao, times(1)).readById(999);
    }

    @Test
    public void testCreateFail() {
        QuestionModel question = new QuestionModel();
        question.setQuestion("O que é necrose?");
        question.setCategory("Feridas");

        when(questionDao.add(question)).thenReturn(-1);

        int result = questionService.create(question);

        assertEquals(-1, result, "A criação deveria falhar retornando -1.");
        verify(questionDao, times(1)).add(question);
    }

    @Test
    public void testDeleteFail() {
        doThrow(new RuntimeException("Erro ao deletar a questão.")).when(questionDao).remove(1);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            questionService.delete(1);
        });

        assertEquals("Erro ao deletar a questão.", exception.getMessage());
        verify(questionDao, times(1)).remove(1);
    }

    @Test
    public void testFindByCategoryFail() {
        when(questionDao.readByCategory("Inexistente")).thenReturn(null);

        QuestionModel result = questionService.findByCategory("Inexistente");

        assertNull(result, "Nenhuma questão deveria ser encontrada para a categoria inexistente.");
        verify(questionDao, times(1)).readByCategory("Inexistente");
    }
}
