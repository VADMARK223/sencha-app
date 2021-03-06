package senchaserver.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import senchaserver.entity.UserEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Markitanov Vadim
 * @since 19.09.2021
 */
@Component
public class JWTUtil {
    public static final String BEARER_ = "Bearer ";
    static String SECRET;

    @Value("${jwt.secret}")
    public void setSecret(String secret) {
        JWTUtil.SECRET = secret;
    }

    public static String createToken(UserEntity userEntity) {
        final Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return JWT.create()
                .withSubject(userEntity.getUsername())
                .withClaim("id", userEntity.getId())
                .withClaim("roles", String.valueOf(userEntity.getRoles()))
                .withExpiresAt(date)
                .sign(Algorithm.HMAC512(SECRET));
    }

    public static String getSubjectByToken(String token) throws JWTVerificationException {
        return JWT.require(Algorithm.HMAC512(JWTUtil.SECRET))
                .build()
                .verify(token)
                .getSubject();
    }
}
