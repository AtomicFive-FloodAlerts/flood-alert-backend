package Atomic5.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Atomic5.demo.dto.MapDTO;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
@CrossOrigin(origins = "*")
public class MapController {
        @GetMapping()
        public ResponseEntity<List<MapDTO>> getMapSpots() {
        List<MapDTO> spots = List.of(
                new MapDTO(
                        1L,
                        "Colombo Fort",
                        "Severe flooding near Colombo Fort",
                        6.9271,
                        79.8612,
                        "HIGH"
                ),
                new MapDTO(
                        2L,
                        "Pettah",
                        "Water rising in Pettah area",
                        6.9100,
                        79.8700,
                        "MEDIUM"
                ),
                new MapDTO(
                        3L,
                        "Galle Face",
                        "Minor flooding reported at Galle Face",
                        6.568545,
                        80.826024,
                        "MEDIUM"
                )
        );

        return ResponseEntity.ok(spots);
        }
      @GetMapping("map:stat")
      public String getMethodName() {
          return new String("Map statistics: 3 flood spots reported");
      }
      

}