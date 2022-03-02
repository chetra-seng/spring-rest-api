package meow.spring.restapi.controllers;

import meow.spring.restapi.exceptions.UserNotFoundException;
import meow.spring.restapi.models.User;
import meow.spring.restapi.repositories.UserRepository;
import meow.spring.restapi.responses.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable  int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }
        return user.get();
    }

    @GetMapping("/users")
    public Set<User> getAllUsers(){
        return new HashSet<>(userRepository.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<MessageResponse> createUser(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<>(new MessageResponse("User Created"), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(new MessageResponse("User Deleted"), HttpStatus.OK);
    }
}
