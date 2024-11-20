package br.fai.backend.heathtraining.beckend.healthtraining.main.service.game;

import br.fai.backend.heathtraining.beckend.healthtraining.main.domain.GameModel;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.service.game.GameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceImplTest {

    @Mock
    private GameDao gameDao;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriar() {
        // Configura o modelo do jogo
        GameModel jogo = new GameModel();
        jogo.setStatus("Ativo");

        // Simula a criação do jogo no DAO
        when(gameDao.add(jogo)).thenReturn(1);

        // Chama o método criar
        int resultado = gameService.create(jogo);

        // Verifica se o ID foi gerado corretamente
        assertEquals(1, resultado);
        verify(gameDao, times(1)).add(jogo); // Garante que o método foi chamado
    }

    @Test
    public void testExcluir() {
        // Chama o método excluir
        gameService.delete(1);

        // Garante que o método de remoção foi chamado no DAO
        verify(gameDao, times(1)).remove(1);
    }

    @Test
    public void testBuscarPorId() {
        // Configura um jogo fictício
        GameModel jogo = new GameModel();
        jogo.setStatus("Concluído");

        // Simula o retorno do jogo pelo ID
        when(gameDao.readById(1)).thenReturn(jogo);

        // Chama o método de busca
        GameModel resultado = gameService.findById(1);

        // Verifica se o jogo foi encontrado corretamente
        assertNotNull(resultado);
        assertEquals("Concluído", resultado.getStatus());
        verify(gameDao, times(1)).readById(1);
    }

    @Test
    public void testBuscarTodos() {
        // Configura uma lista de jogos simulados
        List<GameModel> jogos = new ArrayList<>();
        GameModel jogo = new GameModel();
        jogo.setStatus("Em andamento");
        jogos.add(jogo);

        // Simula o retorno do DAO
        when(gameDao.readAll()).thenReturn(jogos);

        // Chama o método de busca
        List<GameModel> resultado = gameService.findAll();

        // Verifica se a lista foi retornada corretamente
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(gameDao, times(1)).readAll();
    }

    @Test
    public void testAtualizar() {
        // Configura o jogo atual e o jogo atualizado
        GameModel jogoExistente = new GameModel();
        jogoExistente.setId(1);
        jogoExistente.setStatus("Em andamento");

        GameModel jogoAtualizado = new GameModel();
        jogoAtualizado.setStatus("Concluído");

        // Simula o retorno do jogo existente
        when(gameDao.readById(1)).thenReturn(jogoExistente);

        // Chama o método de atualização
        gameService.update(1, jogoAtualizado);

        // Verifica se o método de atualização foi chamado no DAO
        verify(gameDao, times(1)).updateInformation(1, jogoAtualizado);
    }

    @Test
    public void testAtualizarPontos() {
        // Configura um jogo com pontos atualizados
        GameModel jogo = new GameModel();
        jogo.setStatus("Concluído");

        // Simula a atualização dos pontos
        when(gameDao.updatePoints(1, jogo)).thenReturn(true);

        // Chama o método de atualização dos pontos
        boolean resultado = gameService.updatePoints(1, jogo);

        // Verifica se a atualização foi bem-sucedida
        assertTrue(resultado);
        verify(gameDao, times(1)).updatePoints(1, jogo);
    }


}