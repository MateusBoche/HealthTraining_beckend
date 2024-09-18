package br.fai.backend.heathtraining.beckend.healthtraining.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GamePointsDto {
    private int gameId;
    private int userId;
    private int points;
}
