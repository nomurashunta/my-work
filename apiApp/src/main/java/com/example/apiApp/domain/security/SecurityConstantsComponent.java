package com.example.apiApp.domain.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.security-config")
public class SecurityConstantsComponent {

    @Getter
    @Setter
    private String secretKey;

    @Getter
    @Setter
    private String tokenHeader;

    @Getter
    @Setter
    private String tokenPrefix;

}
