package com.academy.educationalplatform.service;

import com.academy.educationalplatform.config.AppConstant;
import com.academy.educationalplatform.exception.PlatformErrorCode;
import com.academy.educationalplatform.exception.PlatformException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;
    private static final Duration CODE_TTL = Duration.ofMinutes(AppConstant.Redis.OTP_EXPIRATION_MINUTES);

    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isResendAllowed(String email, String actionType) {
        String key = AppConstant.Redis.COOLDOWN_PREFIX + actionType + ":" + email;
        return !Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setResendCooldown(String email, String actionType) {
        String key = AppConstant.Redis.COOLDOWN_PREFIX + actionType + ":" + email;
        redisTemplate.opsForValue().set(
                key,
                "1",
                AppConstant.Redis.RESEND_COOLDOWN_SECONDS,
                TimeUnit.SECONDS
        );
    }

    public long getRemainingCooldown(String email, String username, String phone, String actionType) {
        String key = AppConstant.Redis.COOLDOWN_PREFIX + actionType + ":" + email;
        Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return (expire != null && expire > 0) ? expire : 0;
    }

    public void updateRegistrationVerificationCode(String email, String username, String phone, String newVerificationCode) {
        Map<Object, Object> currentData = getRegistrationData(email);
        if (currentData.isEmpty()) {
            throw PlatformException.of(PlatformErrorCode.REGISTRATION_SESSION_EXPIRED);
        }

        String inviteCode = (String) currentData.get("inviteCode");
        storeRegistrationData(email, inviteCode, newVerificationCode, username, phone);
    }


    public void updateResetCode(String email, String newCode) {
        if (getResetCode(email) == null) {
            throw PlatformException.of(PlatformErrorCode.RESET_SESSION_EXPIRED);
        }
        storeResetCode(email, newCode);
    }

    public void storeRegistrationData(String verificationCode, String email, String inviteCode, String username, String phone) {
        String key = AppConstant.Redis.REG_CODE_PREFIX + verificationCode;
        redisTemplate.opsForHash().putAll(key, Map.of(
                "inviteCode", inviteCode != null ? inviteCode : "",
                "email", email != null ? email : "",
                "username", username != null ? username : "",
                "phone", phone != null ? phone : ""
        ));
        redisTemplate.expire(key, CODE_TTL);
    }

    public Map<Object, Object> getRegistrationData(String verificationCode) {
        return redisTemplate.opsForHash().entries(AppConstant.Redis.REG_CODE_PREFIX + verificationCode);
    }

    public void deleteRegistrationData(String verificationCode) {
        redisTemplate.delete(AppConstant.Redis.REG_CODE_PREFIX + verificationCode);
    }

    public void storeResetCode(String email, String code) {
        redisTemplate.opsForValue().set(
                AppConstant.Redis.RESET_PASSWORD_PREFIX + email,
                code,
                CODE_TTL.toMinutes(),
                TimeUnit.MINUTES
        );
    }

    public String getResetCode(String email) {
        return redisTemplate.opsForValue().get(AppConstant.Redis.RESET_PASSWORD_PREFIX + email);
    }

    public void deleteResetCode(String email) {
        redisTemplate.delete(AppConstant.Redis.RESET_PASSWORD_PREFIX + email);
    }
}
