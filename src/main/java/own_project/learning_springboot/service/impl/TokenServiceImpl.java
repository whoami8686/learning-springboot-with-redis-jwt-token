package own_project.learning_springboot.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import own_project.learning_springboot.entity.User;
import own_project.learning_springboot.service.TokenService;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenServiceImpl implements TokenService {

    private final Algorithm algorithm = Algorithm.HMAC256("SecretKey2934723424235345345345345298472947928749234");

    private final JWTVerifier verifier = JWT.require(algorithm).build();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String create(User user){
        String token = JWT.create()
                .withClaim("username", user.getUsername())
                .withClaim("name", user.getName())
                .withExpiresAt(Instant.now().plus(Duration.ofDays(30)))
                .sign(algorithm);

        redisTemplate.opsForValue().set(token, user.getUsername(), Duration.ofDays(30));

        return token;

    }

    @Override
    public User nameFromToken(String token){

        //check the token on redis first
        if(!redisTemplate.hasKey(token)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Expired!");
        }

        // verifying the token
        DecodedJWT jwtToken = verifier.verify(token);

        User user = new User();
        user.setUsername(jwtToken.getClaim("username").asString());
        user.setName(jwtToken.getClaim("name").asString());

        return user;
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 30);
    }

}
