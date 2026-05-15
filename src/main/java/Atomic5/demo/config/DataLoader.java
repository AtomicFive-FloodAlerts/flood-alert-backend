package Atomic5.demo.config;

import Atomic5.demo.model.User;
import Atomic5.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {

                User user = new User();
                user.setName("Test User");
                user.setEmail("test@gmail.com");
                user.setPhoneNumber("0771234567");
                user.setPassword("123456");
                user.setLatitude(6.9271);
                user.setLongitude(79.8612);
                user.setNotificationsEnabled(true);

                userRepository.save(user);

                System.out.println("Default user created with ID: " + user.getId());
            }
        };
    }
}