package br.fai.backend.heathtraining.beckend.healthtraining.main.configuration;

import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.fake.QuestionFakeDaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.fake.UserFakeDaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.h2.UserH2DaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres.GamePostgresDaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres.QuestionPostgresDaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres.UserPostgresDaoImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.game.GameDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.question.QuestionDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.dao.user.UserDao;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.authentication.AuthenticationService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.port.service.user.UserService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.security.JwtService;
import br.fai.backend.heathtraining.beckend.healthtraining.main.security.JwtServiceImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.service.authentication.BasicAuthenticationServiceImpl;
import br.fai.backend.heathtraining.beckend.healthtraining.main.service.authentication.JwtAuthenticationServiceImpl;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;

@Configuration
public class AppConfiguration {

    public  AppConfiguration(){
        System.out.println("aaaaaaaaaaaaa");
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

    @Bean
    @Profile({"prod", "sec"})
    public UserDao getUserDao(final Connection connection){
        return new UserPostgresDaoImpl(connection);
    }

    @Bean
    @Profile("fake")
    public QuestionDao getQuestionFakeDao(){
        return new QuestionFakeDaoImpl();
    }

    @Bean
    @Profile({"prod", "sec"})
    public GameDao getGameDao(final Connection connection){
        return new GamePostgresDaoImpl(connection);
    }

    @Bean
    @Profile({"prod", "sec"})
    public QuestionDao getQuestionDao(final Connection connection){
        return new QuestionPostgresDaoImpl(connection);
    }



    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("LDS")
                        .version("1.0.0")
                        .description("LDS API"));
    }

    @Bean
    @Profile("prod")
    public AuthenticationService basicAuthenticationService(UserService userService, final PasswordEncoder passwordEncoder){
        return new BasicAuthenticationServiceImpl(userService, passwordEncoder);
    }

    @Bean
    @Profile("sec")
    public AuthenticationService jwtAuthenticationService(final UserService userService, final PasswordEncoder passwordEncoder){
        return new JwtAuthenticationServiceImpl(userService, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile("sec")
    public JwtService jwtService(){
        return new JwtServiceImpl();
    }

}
