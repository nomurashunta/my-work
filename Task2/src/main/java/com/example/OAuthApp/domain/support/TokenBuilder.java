package com.example.OAuthApp.domain.support;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

/**
 * JWTトークンを生成するクラスです。
 */
public class TokenBuilder {

    /**
     * ユーザー情報を含むJWTトークンを生成し返します。
     * @param userID ユーザーのID
     * @param username ユーザー名
     * @param emailAddress メールアドレス
     * @return JWTトークンの文字列
     */
    public String build(String userID, String username, String emailAddress) {
        String secretKey = SecurityConstantsUtil.getSecretKey();
        Date issuedAt = new Date();
        Date notBefore = new Date(issuedAt.getTime());
        Date expiresAt = new Date(issuedAt.getTime() + SecurityConstantsUtil.getExpirationTime());
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            String token = JWT.create()
                    .withIssuedAt(issuedAt)
                    .withNotBefore(notBefore)
                    .withExpiresAt(expiresAt)
                    .withClaim("user_id", userID)
                    .withClaim("username", username)
                    .withClaim("email_address", emailAddress)
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
