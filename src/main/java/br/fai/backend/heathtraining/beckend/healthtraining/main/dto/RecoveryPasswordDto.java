package br.fai.backend.heathtraining.beckend.healthtraining.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecoveryPasswordDto {

    private int id;
    private String newPassword;
}
