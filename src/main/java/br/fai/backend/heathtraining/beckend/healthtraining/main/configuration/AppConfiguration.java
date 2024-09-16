package br.fai.backend.heathtraining.beckend.healthtraining.main.configuration;

import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.fake.UserFakeDaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.h2.UserH2DaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres.UserPostgresDaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;

@Configuration
public class AppConfiguration {

    public  AppConfiguration(){
        System.out.println("aaaaaaaaaaaaa");
    }

    @Bean
    @Profile("postgres")
    public UserDao getUserDao(final Connection connection){
        return new UserPostgresDaoImpl(connection);
    }

    @Bean
    @Profile("fake")
    public UserDao getUserFakeDao(){
        return new UserFakeDaoImpl();
    }
    @Bean
    @Profile("dev")
    public UserDao getH2Dao(final JdbcTemplate jdbcTemplate){
        return new UserH2DaoImpl(jdbcTemplate);
    }


}
