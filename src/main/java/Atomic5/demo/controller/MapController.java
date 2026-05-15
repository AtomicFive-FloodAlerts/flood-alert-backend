package Atomic5.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Atomic5.demo.dto.MapDTO;

import java.util.List;

@RestController
@RequestMapping("/api/maps")
@CrossOrigin(origins = "*")
public class MapController {

    private final MapRepository mapRepository;

    public MapController(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    @GetMapping
    public ResponseEntity<List<MapDTO>> getMapSpots() {

        List<MapDTO> spots = mapRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(spots);
    }

    private MapDTO convertToDTO(MapSpot spot) {
        return new MapDTO(
                spot.getId(),
                spot.getTitle(),
                spot.getDescription(),
                spot.getLatitude(),
                spot.getLongitude(),
                spot.getSeverity()
        );
    }

    @GetMapping("/map-stat")
    public String getMapStatistics() {
        long count = mapRepository.count();
        return "Map statistics: " + count + " flood spots reported";
    }
}