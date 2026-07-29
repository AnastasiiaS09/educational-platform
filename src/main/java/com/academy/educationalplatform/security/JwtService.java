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
import java.util.UUID;

@Service
public class JwtService {
    private final byte[] secret;
    private final long expirationMs;
    private final long refreshExpirationMs;

    public JwtService(
            @Value("${store.jwt.secret}") String secret,
            @Value("${store.jwt.expiration-ms}") long expirationMs,
            @Value("${store.jwt.refresh-expiration-ms}") long refreshExpirationMs)
    {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        this.expirationMs = expirationMs;
        this.refreshExpirationMs = refreshExpirationMs;
    }

    public String generateToken(User user, List<Role> roles) {
        return buildToken(String.valueOf(user.getId()), user.getEmail(), roles, expirationMs, "access");
    }

    public String generateRefreshToken(User user, List<Role> roles) {
        return buildToken(String.valueOf(user.getId()), user.getEmail(), roles, refreshExpirationMs, "refresh");
    }

    //tokens for admins

    public SecurityUser parseRefreshToken(String token) {
        try {
            SignedJWT signedJwt = SignedJWT.parse(token);
            if (!signedJwt.verify(new MACVerifier(secret))) {
                throw PlatformException.of(PlatformErrorCode.INVALID_TOKEN);
            }

            JWTClaimsSet claims = signedJwt.getJWTClaimsSet();

            String type = claims.getStringClaim("type");
            if(!"refresh".equals(type)) {
                throw PlatformException.of(PlatformErrorCode.INVALID_TOKEN);
            }

            Date expiration = claims.getExpirationTime();
            if (expiration == null || expiration.before(new Date())) {
                throw PlatformException.of(PlatformErrorCode.INVALID_TOKEN);
            }

            UUID id = UUID.fromString(claims.getSubject());
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
            throw PlatformException.of(PlatformErrorCode.INVALID_TOKEN);
        }
    }

    public SecurityUser parseToken(String token) {
        try {
            SignedJWT signedJwt = SignedJWT.parse(token);
            if (!signedJwt.verify(new MACVerifier(secret))) {
                throw PlatformException.of(PlatformErrorCode.INVALID_TOKEN);
            }

            JWTClaimsSet claims = signedJwt.getJWTClaimsSet();
            Date expiration = claims.getExpirationTime();
            if (expiration == null || expiration.before(new Date())) {
                throw PlatformException.of(PlatformErrorCode.INVALID_TOKEN);
            }

            UUID id = UUID.fromString(claims.getSubject());
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
            throw PlatformException.of(PlatformErrorCode.INVALID_TOKEN);
        }
    }


    private String buildToken(String id, String email, List<Role> roles, long expiration, String type) {
        try {
            Date now = new Date();
            List<String> roleNames = roles.stream().map(Role::name).toList();

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(id)
                    .claim("email", email)
                    .claim("roles", roleNames)
                    .claim("type", type)
                    .issueTime(now)
                    .expirationTime(new Date(now.getTime() + expiration))
                    .build();

            SignedJWT signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);
            signedJwt.sign(new MACSigner(secret));
            return signedJwt.serialize();
        } catch (JOSEException e) {
            throw new IllegalStateException("Failed to generate JWT", e);
        }
    }

    /*public String generateToken(User user, List<Role> roles) {
        try {
            Date now = new Date();

            List<String> roleNames = roles.stream()
                    .map(Role::name)
                    .toList();

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(String.valueOf(user.getId()))
                    .claim("email", user.getEmail())
                    .claim("roles", roleNames)
                    .issueTime(now)
                    .expirationTime(new Date(now.getTime() + expirationMs))
                    .build();

            SignedJWT signedJwt = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claims
            );

            signedJwt.sign(new MACSigner(secret));


            return signedJwt.serialize();

        } catch (RuntimeException e) {
            throw new IllegalStateException("Failed to generate JWT", e);
        } catch (KeyLengthException e) {
            throw new RuntimeException(e);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
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

            UUID id = UUID.fromString(claims.getSubject());
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
    }*/
}
