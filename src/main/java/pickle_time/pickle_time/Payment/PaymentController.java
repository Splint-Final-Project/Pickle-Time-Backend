package pickle_time.pickle_time.Payment;

import java.io.IOException;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequiredArgsConstructor
public class PaymentController {

    // @Value("${iamport.key}")
    // private String restApiKey;
    // @Value("${iamport.secret}")
    // private String restApiSecret;
    
    // iam.api.key=6122282806138807
    // iam.api.secretkey=sIVdngTX2fypGGO1mpmwnXZBfKnCOGp6ZKlwwGZTKxL23nzFpUru4FUWxLQgXXCvEOhKxj8FOw4Y0new

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient("6122282806138807", "sIVdngTX2fypGGO1mpmwnXZBfKnCOGp6ZKlwwGZTKxL23nzFpUru4FUWxLQgXXCvEOhKxj8FOw4Y0new");
    }

    @PostMapping("/verify_iamport/{imp_uid}")
    public IamportResponse<com.siot.IamportRestClient.response.Payment> paymentByImpUid(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException {
      System.out.println(iamportClient.paymentByImpUid(imp_uid));
      // 유효성 검증 후 DB에 저장하기
      // 결제 완료 혹은 실패 메세지로 respond하기
      return iamportClient.paymentByImpUid(imp_uid);
    }
}
