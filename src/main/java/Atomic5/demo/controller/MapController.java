package Atomic5.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/maps")
@CrossOrigin(origins = "*")
public class MapController {
  @GetMapping
  public ResponseEntity<List<Map<String, Object>>> getMap() {
     System.out.println("MapController: Returning mock flood data");
    return ResponseEntity.ok(List.of(
        createFlood(1L, "Kelani River Flood", "Water level rising rapidly", 6.9553, 79.8700, "HIGH"),
        createFlood(2L, "Colombo Street Flooding", "Minor flooding reported", 6.9271, 79.8612, "MEDIUM"),
        createFlood(3L, "Drain Blockage", "Possible water accumulation", 6.9147, 79.8731, "LOW")));
       
  }

  private Map<String, Object> createFlood(Long id, String name, String description,
      Double latitude, Double longitude, String priority) {
    Map<String, Object> flood = new LinkedHashMap<>();
    flood.put("id", id);
    flood.put("name", name);
    flood.put("description", description);
    flood.put("latitude", latitude);
    flood.put("longitude", longitude);
    flood.put("priority", priority);
    return flood;
  }

}