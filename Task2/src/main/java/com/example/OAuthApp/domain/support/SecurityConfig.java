package com.example.OAuthApp.domain.support;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * セキュリティ設定のためのクラスです。
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * セキュリティ設定です。
     * @param http HttpSecurityクラスのオブジェクト
     * @throws Exception 例外
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        //認証設定
        http
                .authorizeRequests()
                .antMatchers("/login**", "/error_403")
                .permitAll()
                .anyRequest()
                .authenticated();

        //ログイン画面設定
        http
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/top", true);

        //認証なしでアクセス時の設定
        http
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomHttp403ForbiddenEntryPoint());

        //ログアウト時の設定
        http
                .logout()
                .clearAuthentication(true)
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    /**
     * 認証なしアクセス時の処理に必要なハンドラです。
     */
    public class CustomAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException arg2)
                throws IOException, ServletException {
        }
    }

    /**
     * 認証なしアクセス時にエラー画面を表示するための処理を行います。
     */
    public class CustomHttp403ForbiddenEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response,
                             AuthenticationException authException) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
            redirectStrategy.sendRedirect(request, response, "/error_403");
        }
    }
}
