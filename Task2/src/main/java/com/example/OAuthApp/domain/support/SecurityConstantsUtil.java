package com.example.OAuthApp.domain.support;

import com.example.OAuthApp.domain.component.SecurityConstantsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

/**
 * 設定用のユーティリティクラス
 */
@Component
public class SecurityConstantsUtil {

    static SecurityConstantsComponent component;

    @Autowired
    private SecurityConstantsComponent autoWiredComponent;

    @PostConstruct
    private void init(){
        SecurityConstantsUtil.component = autoWiredComponent;
    }

    public static String getSecretKey(){
        return component.getSecretKey();
    }

    public static Long getExpirationTime(){
        return component.getExpirationTime();
    }

}