package pickle_time.pickle_time.Scrap.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Scrap", description = "찜 관련 API")
@RestController
public class ScrapController {

    private ScrapService scrapService;

    public ScrapController(ScrapService scrapService) {
        this.scrapService = scrapService;
    }


    @Operation(
            summary = "찜 추가.",
            description = "특정 피클을 찜 목록에 추가합니다"
    )
    @PutMapping("/api/v1/pickle/{pickleId}/scrap")
    public ResponseEntity<?> scrapPickle(@PathVariable Long pickleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        Long userId = Long.parseLong(userDetails.getUsername());

        Scrap scrap = scrapService.scrap(userId, pickleId);
        Pickle pickle = scrap.getPickle();

        return ResponseEntity.ok(new ApiResponse<>(true,new ScrapResponse(pickle.getTitle(),pickle.getId() ),null));
    }

    @Operation(
            summary = "찜 제거",
            description = "특정 피클을 찜 목록에서 제거합니다."
    )
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
