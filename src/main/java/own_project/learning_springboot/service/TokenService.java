package own_project.learning_springboot.service;

import own_project.learning_springboot.entity.User;

public interface TokenService {

    String create(User user);

    User nameFromToken(String token);

}
