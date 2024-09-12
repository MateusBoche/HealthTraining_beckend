package br.fai.backend.heathtraining.beckend.healthtraining.main.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameModel {
    private int id; // String front
    private String status;
    private int nivelAtual;
    //private int usuarioID;
    private int numeroAcertos;
    private int numeroErros;
    private String dataDeCriacao;

}
