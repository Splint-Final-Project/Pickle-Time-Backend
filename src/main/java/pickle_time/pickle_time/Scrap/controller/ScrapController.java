package pickle_time.pickle_time.Scrap.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pickle_time.pickle_time.Pickle.model.Pickle;
import pickle_time.pickle_time.Scrap.dto.ScrapResponse;
import pickle_time.pickle_time.Scrap.model.Scrap;
import pickle_time.pickle_time.Scrap.service.ScrapService;
import pickle_time.pickle_time.global.dto.ApiResponse;

@RestController
public class ScrapController {

    private ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }

    @PutMapping("/api/v1/pickle/{pickleId}/scrap")
    public ResponseEntity<?> scrapPickle(@PathVariable Long pickleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Long userId = Long.parseLong(userDetails.getUsername());

        Scrap scrap = scrapService.scrap(userId, pickleId);
        Pickle pickle = scrap.getPickle();

        return ResponseEntity.ok(new ApiResponse<>(true,new ScrapResponse(pickle.getTitle(),pickle.getId() ),null));
    }

    @DeleteMapping("/api/v1/pickle/{pickleId}/scrap")
    public ResponseEntity<?> scrapDelete(@PathVariable Long pickleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Long userId = Long.parseLong(userDetails.getUsername());

        Scrap scrap = scrapService.cancel(userId, pickleId);
        Pickle pickle = scrap.getPickle();

        return ResponseEntity.ok(new ApiResponse<>(true, new ScrapResponse(pickle.getTitle(), pickle.getId()),null));
    }
}
