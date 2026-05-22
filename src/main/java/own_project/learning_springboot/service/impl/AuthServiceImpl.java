package own_project.learning_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import own_project.learning_springboot.entity.User;
import own_project.learning_springboot.model.LoginUserRequest;
import own_project.learning_springboot.model.TokenResponse;
import own_project.learning_springboot.repository.UserRepository;
import own_project.learning_springboot.security.BCrypt;
import own_project.learning_springboot.service.AuthService;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationServiceImpl validationService;

    @Autowired
    private TokenServiceImpl tokenService;

    @Transactional
    @Override
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password!"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            //user.setToken(UUID.randomUUID().toString()); won't be needing this again because we will not using database anymore
            //user.setTokenExpiredAt(next30Days());
            //userRepository.save(user);

            String token = tokenService.create(user);

            return TokenResponse.builder()
                    .token(token)
                    //.expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong username or password!");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);
    }

    @Transactional
    @Override
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }
}
