package pickle_time.pickle_time.Pickle;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pickle_time.pickle_time.Member.dto.JoinRequest;

@RestController
@RequiredArgsConstructor
public class PickleController {

  private final PickleService pickleService;

//   @PostMapping("/")
//     public ResponseEntity<?> join(@RequestBody JoinRequest joinRequest) {
//         try {

//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }
    
//     @GetMapping("/")
//     public ResponseEntity<List<Member>> getAllMembers() {
//         List<Member> members = memberService.findAll();
//         return ResponseEntity.ok(members);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<?> getMemberById(@PathVariable Long id) {
//         Optional<Member> member = memberService.findById(id);
//         return member.isPresent() ? ResponseEntity.ok(member.get()) : ResponseEntity.notFound().build();
//     }

}
