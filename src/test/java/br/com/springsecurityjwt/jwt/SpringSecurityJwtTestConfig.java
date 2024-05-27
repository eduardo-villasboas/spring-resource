package br.com.springsecurityjwt.jwt;

import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = true)
@TestPropertySource(
        properties = "classpath:application-test.properties"
)
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
}
