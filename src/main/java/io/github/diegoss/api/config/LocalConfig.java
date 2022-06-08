package io.github.diegoss.api.config;

import io.github.diegoss.api.domain.User;
import io.github.diegoss.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB(){
        User user = new User(null, "Diego", "Diego@email.com", "123");
        User user1 = new User(null, "Karina", "Karina@email.com", "123");
        User user2 = new User(null, "Cida", "Cida@email.com", "123");

        userRepository.saveAll(List.of(user, user1, user2));
    }
}
