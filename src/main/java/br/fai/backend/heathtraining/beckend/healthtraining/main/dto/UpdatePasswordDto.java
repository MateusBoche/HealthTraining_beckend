package br.fai.backend.heathtraining.beckend.healthtraining.main.dto;

import lombok.Data;

@Data
public class UpdatePasswordDto {
    private int id;
    private String oldPassword;
    private String newPassword;
}
