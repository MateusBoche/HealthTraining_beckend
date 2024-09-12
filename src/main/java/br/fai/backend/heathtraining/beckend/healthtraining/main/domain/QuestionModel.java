package br.fai.backend.heathtraining.beckend.healthtraining.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {
    private String question;
    private boolean answer;
    private String category;
    private int id; // string front
    private int phase;

}
