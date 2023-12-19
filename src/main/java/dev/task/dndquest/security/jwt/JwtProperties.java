package dev.task.dndquest.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt.token")
public record JwtProperties(String secretKey, int validityTime) {
}
