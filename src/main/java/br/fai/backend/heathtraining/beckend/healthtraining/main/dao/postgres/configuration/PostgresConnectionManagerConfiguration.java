package br.fai.backend.heathtraining.beckend.healthtraining.main.dao.postgres.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@Profile("postgres")
public class PostgresConnectionManagerConfiguration {

    @Value("${spring.datasource.base.url}")
    private  String databaseBaseUrl;

    @Value("${spring.datasource.url}")
    private  String databaseUrl;

    @Value("${spring.datasource.username}")
    private  String databaseUsername;

    @Value("${spring.datasource.password}")
    private  String databasePassword;


    @Bean
    public DataSource dataSource() throws SQLException {
        final DataSource build = DataSourceBuilder
                .create()
                .url(databaseBaseUrl)
                .username(databaseUsername)
                .password(databasePassword)
                .build();
        final Connection connection = build.getConnection();
        createDatabaseIfNotExists(connection);
        return build;
    }

    private void createDatabaseIfNotExists(Connection connection) {
    }

    @Bean
    @DependsOn("dataSource")
    public Connection getConnection() throws  SQLException{
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(databaseUrl);
        hikariConfig.setUsername(databaseUsername);
        hikariConfig.setPassword(databasePassword);
        return new HikariDataSource(hikariConfig).getConnection();

    }

}
