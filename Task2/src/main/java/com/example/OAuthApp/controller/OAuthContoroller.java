package com.example.OAuthApp.controller;

import com.example.OAuthApp.domain.service.JwtTokenService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;

/**
 * OAuth認証アプリのメインコントローラークラスです。
 */
@Controller
public class OAuthContoroller {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Data
    private class Provider{

        Provider(String name, String url){
            this.name = name;
            this.url = url;
        }

        String name;
        String url;
    }

    private static String authorizationRequestBaseUri
            = "oauth2/authorization/";

    private final OAuth2AuthorizedClientService authorizedClientService;

    /**
     * コンストラクタ
     * @param authorizedClientService
     */
    public OAuthContoroller(OAuth2AuthorizedClientService authorizedClientService){
        this.authorizedClientService = authorizedClientService;

    }

    /**
     * /top へのアクセスを処理します。
     * ログインされていない場合、/login へリダイレクトします。
     * ログインされている場合、ユーザーの情報とJWTトークンを表示するための処理を行います。
     * @param model Modelクラスのオブジェクト
     * @return top.html
     */
    @GetMapping("/top")
    public String top(Model model){

        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof OAuth2AuthenticationToken)) return "redirect:/login";

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthorizedClient authorizedClient = this.getAuthorizedClient(oAuth2AuthenticationToken);
        if (authorizedClient == null)  return "redirect:/login";

        model.addAttribute("client", authorizedClient.getClientRegistration().getClientName());
        OAuth2User user = oAuth2AuthenticationToken.getPrincipal();
        model.addAttribute("attributes", user.getAttributes());

        String jwtToken = jwtTokenService.getToken(user.getAttributes());
        model.addAttribute("jwtToken", jwtToken);

        return "top";
    }

    /**
     * /login へのアクセスを処理します。
     * 登録されたOAuthプロバイダを画面に表示するための処理を行い、ログイン画面を返します。
     * @param model Modelクラスのオブジェクト
     * @return login.html
     */
    @GetMapping(value = "/login")
    public String login(Model model){
        Iterable<ClientRegistration> clientRegistrations = null;
        List<Provider> providerList = new ArrayList<>();
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        clientRegistrations.forEach(registration -> {
                    providerList.add(new Provider(registration.getClientName(),
                            authorizationRequestBaseUri + registration.getRegistrationId()));
                }
        );
        model.addAttribute("providers", providerList);
        return "login";
    }

    /**
     * 認証されていないときのエラー画面です。
     * @return error_403.html
     */
    @GetMapping("/error_403")
    public String error_403(){
        return "error_403";
    }

    private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authenticationToken){
        return this.authorizedClientService.loadAuthorizedClient(
                authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getName()
        );
    }

}
