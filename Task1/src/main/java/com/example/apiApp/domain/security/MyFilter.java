package com.example.apiApp.domain.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.common.utils.SecurityConstantsUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWTトークン認証のためのフィルタークラスです。
 */
public class MyFilter extends BasicAuthenticationFilter {

    /**
     * コンストラクタ
     * @param authenticationManager AuthenticationManagerクラスのオブジェクト
     */
    public MyFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * フィルターの設定を行います。
     * @param request
     * @param response
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        try{
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            if (authentication == null) {
                filterChain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException | JWTVerificationException e){
            //コントローラー側で例外をthrowし、ExceptionHandlerでキャッチする
            RequestDispatcher rd = request.getRequestDispatcher(
                    "/filter_error");
            rd.forward(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws JWTVerificationException, IllegalArgumentException{

        TokenVerifier verifier = new TokenVerifier();

        String token = request.getHeader(SecurityConstantsUtil.getTokenHeader());

        if (token != null && token.startsWith(SecurityConstantsUtil.getTokenPrefix())){
            token = token.replace(SecurityConstantsUtil.getTokenPrefix() + " ", "");
            verifier.verify(token);
            return new UsernamePasswordAuthenticationToken(null, null, null);
        }
        return null;
    }
}
