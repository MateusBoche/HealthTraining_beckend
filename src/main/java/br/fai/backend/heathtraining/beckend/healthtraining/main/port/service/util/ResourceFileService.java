package br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.util;

import java.io.IOException;

public interface ResourceFileService {
    String read(final String resorcePath) throws IOException;
}
