package br.com.springsecurityjwt.jwt;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(SpringSecurityJwtTestConfig.class)
@Sql(value = {"/sql-for-tests/database-initialization/initialize-database.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql-for-tests/database-initialization/clear-database.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class BaseTestComponent {
    /*Base classe for IntegrationTests. It is not named like this becase maven integration tests
        plugin, automatically gets all classes with this suffix ;-)
     */



}