package peakle_time.peakle_time.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @PostMapping("/user")
//    public String join(JoinRequest joinRequest) {
//        memberService.save(joinRequest);
//        return "redirect:/login";
//    }
}
