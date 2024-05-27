package pickle_time.pickle_time.Pickle;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

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
//     public ResponseEntity<List<Users>> getAllMembers() {
//         List<Users> members = memberService.findAll();
//         return ResponseEntity.ok(members);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<?> getMemberById(@PathVariable Long id) {
//         Optional<Users> member = memberService.findById(id);
//         return member.isPresent() ? ResponseEntity.ok(member.get()) : ResponseEntity.notFound().build();
//     }

}
