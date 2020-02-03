package com.example.OAuthApp.domain.service;

import com.example.OAuthApp.domain.component.SecurityConstantsComponent;
import com.example.OAuthApp.domain.support.TokenBuilder;
import com.example.OAuthApp.domain.support.UserInfoKeysConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * JWRトークンを生成するためのServiceクラスです。
 */
@Service
public class JwtTokenService {

    @Autowired
    SecurityConstantsComponent component;

    /**
     * JWTトークンを返します。
     * @param attributes ユーザー情報のHashMap
     * @return JWTトークンの文字列
     */
    public String getToken(Map<String, Object> attributes){
        TokenBuilder builder = new TokenBuilder();

        String jwtToken = builder.build(
                String.valueOf(attributes.getOrDefault(UserInfoKeysConstants.ID, "null")),
                String.valueOf(attributes.getOrDefault(UserInfoKeysConstants.NAME, "null")),
                String.valueOf(attributes.getOrDefault(UserInfoKeysConstants.EMAIL, "null")));

        return jwtToken;
    }

}
