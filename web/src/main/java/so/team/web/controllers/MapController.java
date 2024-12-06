package so.team.web.controllers;

import so.team.web.services.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @GetMapping("/api/map")
    public ResponseEntity<String> getMapData(@RequestParam String location) {
        String response = mapService.fetchMapData(location);
        return ResponseEntity.ok(response);
    }
}