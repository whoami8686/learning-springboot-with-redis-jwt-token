package own_project.learning_springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import own_project.learning_springboot.entity.User;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello(User user){
        return "Hello " + user.getName();
    }

}
