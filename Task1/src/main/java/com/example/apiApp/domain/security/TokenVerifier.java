package com.example.apiApp.domain.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.common.utils.SecurityConstantsUtil;


/**
 * トークン検証を行うクラスです。
 */
public class TokenVerifier {

    /**
     * トークンの検証を行います。
     * @param token JWTトークンの文字列
     * @throws JWTVerificationException
     * @throws IllegalArgumentException
     */
    public void verify(String token) throws JWTVerificationException, IllegalArgumentException {

        String secretKey = SecurityConstantsUtil.getSecretKey();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT jwt = verifier.verify(token);

    }

}
