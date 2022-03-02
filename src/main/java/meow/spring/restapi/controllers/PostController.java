package meow.spring.restapi.controllers;

import meow.spring.restapi.exceptions.UserNotFoundException;
import meow.spring.restapi.models.Post;
import meow.spring.restapi.models.User;
import meow.spring.restapi.repositories.PostRepository;
import meow.spring.restapi.repositories.UserRepository;
import meow.spring.restapi.responses.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("users/{id}/posts")
    public Set<Post> getAllPosts(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }

        return new HashSet<>(user.get().getPosts());
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<MessageResponse> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("User not found");
        }

        post.setUser(user.get());
        postRepository.save(post);

        return new ResponseEntity<MessageResponse>(new MessageResponse("Post created"), HttpStatus.CREATED);
    }
}
