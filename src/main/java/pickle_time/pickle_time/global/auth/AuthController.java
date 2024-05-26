package pickle_time.pickle_time.global.auth;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.io.IOException;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Value("${auth.kakao}")
    private String kakaoKey;

    @GetMapping("/login/kakao")
    public String login(HttpServletResponse response) throws IOException {
        String url = "https://kauth.kakao.com/oauth/authorize";
        response.sendRedirect(url + "?client_id=" + kakaoKey + "&redirect_uri=http://localhost:8080/oauth/login/kakao/result" + "&response_type=code");

        return "ok";
    }

    @GetMapping("/login/kakao/result")
    public void handleCode(@RequestParam(name = "code") String authorizationCode) {
        System.out.println(authorizationCode);

        String url = "https://kauth.kakao.com/oauth/token";
        String grant_type = "authorization_code";
        String client_id = kakaoKey;
        String redirect_uri = "http://localhost:8080/oauth/login/kakao/result";
        String code = authorizationCode;

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

        parameters.add("client_id", client_id);
        parameters.add("client_secret", kakaoKey);
        parameters.add("redirect_uri", redirect_uri);
        parameters.add("code", code);
        parameters.add("grant_type", grant_type);

        RestClient restClient = RestClient.create();
        ResponseEntity<KaKaoToken> response  = restClient.post()
                .uri(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(parameters)
                .retrieve()
                .toEntity(KaKaoToken.class);

        KaKaoToken token = response.getBody();
        System.out.println(token.getAccess_token());


        ResponseEntity<KaKaoUserDto> kakao = restClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer " + token.getAccess_token())
                .retrieve()
                .toEntity(KaKaoUserDto.class);

        KaKaoUserDto test = kakao.getBody();
        System.out.println(test.getKaKaoAccount().getProfile().getNickname());
        System.out.println(test.getKaKaoAccount().getEmail());


    }



}
