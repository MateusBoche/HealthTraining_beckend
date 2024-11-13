package br.fai.backend.heathtraining.beckend.healthtraining.main.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenDto {
    private String token;
}
