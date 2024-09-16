package br.fai.backend.heathtraining.beckend.healthtraining;

import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.util.ResourceFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HealthtrainingApplication {


//	@Autowired
//	private ResourceFileService resourceFileService;
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;



  public static void main(String[] args) {
		SpringApplication.run(HealthtrainingApplication.class, args);
	}

}
