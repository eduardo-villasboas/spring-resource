package br.com.springsecurityjwt;

import br.com.springsecurityjwt.resource.model.TBUser;
import br.com.springsecurityjwt.resource.model.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(
        scanBasePackages = {
                "br.com.springsecurityjwt"
        }
)
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    // Encripted password for user using BCrypt encoder
    @Bean
    ApplicationRunner runner(PasswordEncoder passwordEncoder) {
        final Optional<TBUser> user = userRepository.findByUsername("username");
        if (user.isEmpty()) {
            final TBUser defaultUser = new TBUser();
            defaultUser.setUsername("username");
            defaultUser.setPassword(passwordEncoder.encode("password"));
            userRepository.save(defaultUser);
        }
        return args -> System.out.println(passwordEncoder.encode("password"));
    }
    /*
    TODO: Configurar //https://github.com/songrgg/swaggerdemo/blob/master/pom.xml

     */

}
