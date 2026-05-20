package own_project.learning_springboot.service;

import own_project.learning_springboot.entity.User;
import own_project.learning_springboot.model.RegisterUserRequest;
import own_project.learning_springboot.model.UpdateUserRequest;
import own_project.learning_springboot.model.UserResponse;

public interface UserService {

    void register(RegisterUserRequest request);

    UserResponse get(User user);

    UserResponse update(User user, UpdateUserRequest request);
}
