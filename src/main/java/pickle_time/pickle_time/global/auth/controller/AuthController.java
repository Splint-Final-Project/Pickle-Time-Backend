package pickle_time.pickle_time.global.auth.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pickle_time.pickle_time.global.auth.dto.JwtDTO;

@RestController
public class AuthController {

    @GetMapping("/auth/success")
    public ResponseEntity<JwtDTO> success(@RequestParam String accessToken) {
        return new ResponseEntity<>(new JwtDTO(accessToken),HttpStatus.OK);
    }
}
