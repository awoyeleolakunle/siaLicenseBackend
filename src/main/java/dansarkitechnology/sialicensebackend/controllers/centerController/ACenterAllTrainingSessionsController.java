package dansarkitechnology.sialicensebackend.controllers.centerController;


import dansarkitechnology.sialicensebackend.Utils.ApiResponse;
import dansarkitechnology.sialicensebackend.services.center.CenterService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sialicence+/center/")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ACenterAllTrainingSessionsController {
    private final CenterService centerService;

    @SneakyThrows
    @GetMapping("aCenterAllTrainingSessions")
    public ResponseEntity<ApiResponse> findACenterAllTrainingSessions(@RequestParam String emailAddress) {
        return new ResponseEntity<>(centerService.findACenterAllTrainingSessions(emailAddress), HttpStatus.OK);
    }
}
