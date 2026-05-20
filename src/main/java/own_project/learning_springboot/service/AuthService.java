package own_project.learning_springboot.service;

import own_project.learning_springboot.entity.User;
import own_project.learning_springboot.model.LoginUserRequest;
import own_project.learning_springboot.model.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginUserRequest request);

    void logout(User user);
}
