package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.crud;

import java.util.List;

public interface ReadService<T> {
    T findById(final int id);
    List<T> findAll();
}

