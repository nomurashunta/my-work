package com.example.apiApp.utils;

import com.example.apiApp.domain.security.SecurityConstantsComponent;
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

    public static String getTokenHeader(){
        return component.getTokenHeader();
    }

    public static String getTokenPrefix(){
        return component.getTokenPrefix();
    }

}