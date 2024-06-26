package jwt;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.runners.model.InitializationError;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;


@Configuration
public class SpringSecurityJwtTestConfig {

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgreSQLContainer() {
        return new PostgreSQLContainer<>("postgres:16.3");
    }

    @Bean
    JdbcConnectionDetails jdbcConnectionDetails(final PostgreSQLContainer postgreSQLContainer) {
        return new JdbcConnectionDetails() {
            @Override
            public String getUsername() {
                return postgreSQLContainer.getUsername();
            }

            @Override
            public String getPassword() {
                return postgreSQLContainer.getPassword();
            }

            @Override
            public String getJdbcUrl() {
                return postgreSQLContainer.getJdbcUrl();
            }

        };
    }

    @Bean
    HikariDataSource dataSource(JdbcConnectionDetails jdbcConnectionDetails) {
        final HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(jdbcConnectionDetails.getJdbcUrl());
        hikariConfig.setUsername(jdbcConnectionDetails.getUsername());
        hikariConfig.setPassword(jdbcConnectionDetails.getPassword());
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setMaximumPoolSize(100);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public Flyway flyway(final DataSource dataSource) {
        final FluentConfiguration configure = Flyway
                .configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .schemas("public")
                .communityDBSupportEnabled(true)
                .createSchemas(true);
        final Flyway flyway = new Flyway(configure);
        flyway.migrate();
        return flyway;
    }
}
