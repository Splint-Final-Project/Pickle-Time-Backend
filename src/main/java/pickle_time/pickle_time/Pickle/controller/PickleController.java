package pickle_time.pickle_time.Pickle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import pickle_time.pickle_time.Pickle.dto.request.CreatePickleRequest;
import pickle_time.pickle_time.Pickle.dto.request.UpdatePickleRequest;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.Pickle.service.PickleService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pickles")
public class PickleController {

  private final PickleService pickleService;

  @PostMapping
  public ResponseEntity<?> createPickle(@RequestBody CreatePickleRequest request) {
    try {
      Pickle pickle = pickleService.createPickle(request);
      return ResponseEntity.ok(pickle);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getPickleById(@PathVariable Long id) {
    Pickle pickle = pickleService.findById(id);
    if (pickle != null) {
      return ResponseEntity.ok(pickle);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Pickle>> getAllPickles() {
    List<Pickle> pickles = pickleService.findAll();
    return ResponseEntity.ok(pickles);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updatePickle(@PathVariable Long id, @RequestBody UpdatePickleRequest updatePickleRequest) {
    try {
      Pickle pickle = pickleService.updatePickle(id, updatePickleRequest);
      return ResponseEntity.ok(pickle);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deletePickle(@PathVariable Long id) {
    try {
      pickleService.deletePickle(id);
      return ResponseEntity.ok("피클 삭제 성공");
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


//   @PostMapping("/")
//     public ResponseEntity<?> join(@RequestBody UserJoinRequest joinRequest) {
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
