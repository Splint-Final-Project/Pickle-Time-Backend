package pickle_time.pickle_time.Payment;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    // iam.api.key=6122282806138807
    // iam.api.secretkey=sIVdngTX2fypGGO1mpmwnXZBfKnCOGp6ZKlwwGZTKxL23nzFpUru4FUWxLQgXXCvEOhKxj8FOw4Y0new

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient("6122282806138807", "sIVdngTX2fypGGO1mpmwnXZBfKnCOGp6ZKlwwGZTKxL23nzFpUru4FUWxLQgXXCvEOhKxj8FOw4Y0new");
    }

    @PostMapping("/verify_iamport/{imp_uid}")
    public ResponseEntity<?> verify_payment(@PathVariable("imp_uid") String imp_uid) throws IamportResponseException, IOException {
      try {
        IamportResponse<com.siot.IamportRestClient.response.Payment> response = iamportClient.paymentByImpUid(imp_uid);
        if (response.getResponse().getStatus().equals("paid")) {
          // 결제 금액이 정확한지 확인 (아니라면 환불처리 후 결제실패 처리)
          // Participant 정보에 신규(pending?) 등록하기
          System.out.println("success");
          return ResponseEntity.ok("결제 성공");
        } else {
          System.out.println("fail");
          return ResponseEntity.badRequest().body("결제 실패. 관리자 문의");
        }
        } catch (IamportResponseException e) {
        if (e.getHttpStatusCode() == 401) {
            return new ResponseEntity<>("포트원 request 토큰 오류. 관리자 문의.", HttpStatus.PAYMENT_REQUIRED);
        } else if (e.getHttpStatusCode() == 404) {
            return new ResponseEntity<>("유효하지 않은 imp_uid. 관리자 문의.", HttpStatus.PAYMENT_REQUIRED);
        } else {
            return new ResponseEntity<>("확인 되지 않는 오류, 포트원 문의 필요", HttpStatus.PAYMENT_REQUIRED);
        }
        } catch (IOException e) {
        return new ResponseEntity<>("IO 오류", HttpStatus.PAYMENT_REQUIRED);
        }
    }
}
