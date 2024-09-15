package br.fai.backend.heathtraining.beckend.healthtraining.main.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfiguration {

    public  AppConfiguration(){
        System.out.println("aaaaaaaaaaaaa");
    }

//    @Bean
//    @Profile("fake")
//    public UserDao getUserFakeDao(){
//        return new UserFakeDaoImpl();
//    }
//    @Bean
//    @Profile("dev")
//    public UserDao getH2Dao(final JdbcTemplate jdbcTemplate){
//        return new UserH2DaoImpl(jdbcTemplate);
//    }


}
