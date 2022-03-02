package meow.spring.restapi.bootstraps;

import meow.spring.restapi.models.User;
import meow.spring.restapi.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserBootStrap implements CommandLineRunner {
    private final UserRepository userRepository;

    public UserBootStrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User meow = new User();
        meow.setFirstname("Meow");
        meow.setLastname("Meow");
        meow.setAge(11);
        userRepository.save(meow);

        User chetra = new User();
        chetra.setFirstname("Chetra");
        chetra.setLastname("Seng");
        chetra.setAge(22);
        userRepository.save(chetra);
    }
}
