package peakle_time.peakle_time.global.auth;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Value("${auth.kakao}")
    private String kakaoKey;

    @GetMapping("/login/kakao")
    public String login(HttpServletResponse response) throws IOException {
        String url = "https://kauth.kakao.com/oauth/authorize";


//        https://kauth.kakao.com/oauth/authorize?client_id=86a8d081a1751bfd62c3c49e7d2c3f72&redirect_uri=http://localhost:8080/oauth/login/kakao/result&response_type=code

//        https://kauth.kakao.com/oauth/authorize?client_id=86a8d081a1751bfd62c3c49e7d2c3f72&redirect_uri=http://localhost:8080/oauth/login/kakao/result&response_type=code
        response.sendRedirect(url + "?client_id=" + kakaoKey + "&redirect_uri=http://localhost:8080/oauth/login/kakao/result" + "&response_type=code");
        return "ok";
    }

    @GetMapping("/login/kakao/result")
    public void handleCode(@RequestParam(name = "code") String code) {
        System.out.println(code);
    }



}
