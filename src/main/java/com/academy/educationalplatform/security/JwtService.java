package com.academy.educationalplatform.security;

import com.academy.educationalplatform.entity.Role;
import com.academy.educationalplatform.entity.User;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    private final byte[] secret;
    private final long expirationMs;

    public JwtService(
            @Value("${store.jwt.secret}") String secret,
            @Value("${store.jwt.expiration-ms}") long expirationMs) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        this.expirationMs = expirationMs;
    }

    public String generateToken(User user, List<Role> roles) {
        try {
            Date now = new Date();
            List<String> roleNames = roles.stream().map(Role::name).toList();

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(String.valueOf(user.getId()))
                    .claim("email", user.getEmail())
                    .claim("roles", roleNames)
                    .issueTime(now)
                    .expirationTime(new Date(now.getTime() + expirationMs))
                    .build();

            SignedJWT signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
            signedJwt.sign(new MACSigner(secret));
            return signedJwt.serialize();
        } catch (JOSEException e) {
            throw new IllegalStateException("Failed to generate JWT", e);
        }
    }

    public SecurityUser parseToken(String token) {
        try {
            SignedJWT signedJwt = SignedJWT.parse(token);
            if (!signedJwt.verify(new MACVerifier(secret))) {
                throw PlatformException.of(PlatformErrorCode.INVALID_CREDENTIALS);
            }

            JWTClaimsSet claims = signedJwt.getJWTClaimsSet();
            Date expiration = claims.getExpirationTime();
            if (expiration == null || expiration.before(new Date())) {
                throw PlatformException.of(PlatformErrorCode.INVALID_CREDENTIALS);
            }

            Long id = Long.parseLong(claims.getSubject());
            String email = claims.getStringClaim("email");
            List<Role> roles = claims.getStringListClaim("roles").stream()
                    .map(Role::valueOf)
                    .toList();

            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setPassword("");
            return new SecurityUser(user, roles);
        } catch (ParseException | JOSEException e) {
            throw PlatformException.of(PlatformErrorCode.INVALID_CREDENTIALS);
        }
    }
}
