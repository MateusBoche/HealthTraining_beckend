package br.fai.backend.heathtraining.beckend.healthtraining.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private int id; //string front
    private String nomeCompleto;
    private String email;
    private String senha;
}
