package Atomic5.demo.controller;

import Atomic5.demo.dto.FloodReportDTO;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.FloodSeverity;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.FloodReportRepository;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.service.AlertService;
import Atomic5.demo.service.FloodSeverityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/floods")
@CrossOrigin(origins = "*")
public class FloodReportController {

    private final FloodReportRepository floodReportRepository;
    private final UserRepository userRepository;
    private final AlertService alertService;
    private final FloodSeverityService floodSeverityService;

    public FloodReportController(FloodReportRepository floodReportRepository,
            UserRepository userRepository,
            AlertService alertService,
            FloodSeverityService floodSeverityService) {
        this.floodReportRepository = floodReportRepository;
        this.userRepository = userRepository;
        this.alertService = alertService;
        this.floodSeverityService = floodSeverityService;
    }

    /**
     * Report a new flood
     */
    @PostMapping("/report")
    public ResponseEntity<?> reportFlood(@RequestBody FloodReportDTO reportDTO) {

         System.out.println("===== REPORT ENDPOINT HIT =====");
        try {
            // Get the reporting user
            User reporter = userRepository.findAll().stream().findFirst().orElse(null);

            if (reporter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No users exist in database");
            }

            // Create flood report
            FloodReport report = new FloodReport(
                    reporter,
                    reportDTO.getLatitude(),
                    reportDTO.getLongitude(),
                    reportDTO.getDescription(),
                    reportDTO.getWaterLevel(),
                    reportDTO.getAreaName());

            // Calculate severity based on water level
            FloodSeverity severity = floodSeverityService.calculateSeverityFromWaterLevel(
                    reportDTO.getWaterLevel());
            report.setSeverity(severity);

            // Save the flood report
            FloodReport savedReport = floodReportRepository.save(report);

            // Generate alerts for nearby users
            //alertService.generateAlertsForFloodReport(savedReport);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Flood report created and alerts generated");
        } catch (Exception e) {
            e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating flood report: " + e.getMessage());
        }
    }

    /**
     * Get all active flood reports
     */
    @GetMapping("/active")
    public ResponseEntity<List<FloodReport>> getActiveFloods() {
        List<FloodReport> activeReports = floodReportRepository.findActiveReports(LocalDateTime.now());
        return ResponseEntity.ok(activeReports);
    }

    /**
     * Get flood reports in a specific area (bounding box)
     */
    @GetMapping("/area")
    public ResponseEntity<List<FloodReport>> getFloodsInArea(
            @RequestParam Double minLat,
            @RequestParam Double maxLat,
            @RequestParam Double minLon,
            @RequestParam Double maxLon) {
        List<FloodReport> reports = floodReportRepository.findReportsInArea(
                minLat, maxLat, minLon, maxLon, LocalDateTime.now());
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{floodId}")
    public ResponseEntity<FloodReport> getFloodById(@PathVariable Long floodId) {
        return floodReportRepository.findById(floodId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/map")
    public ResponseEntity<List<Map<String, Object>>> getMapData() {

        List<FloodReport> reports = floodReportRepository.findAll();

        List<Map<String, Object>> result = reports.stream().map(report -> {

            Map<String, Object> data = new HashMap<>();

            data.put("id", report.getId());
            data.put("name", report.getAreaName());
            data.put("description", report.getDescription());
            data.put("latitude", report.getLatitude());
            data.put("longitude", report.getLongitude());

            FloodSeverity severity = report.getSeverity();

            if (severity == FloodSeverity.HIGH || severity == FloodSeverity.CRITICAL) {
                data.put("priority", "HIGH");
            } else if (severity == FloodSeverity.MODERATE) {
                data.put("priority", "MEDIUM");
            } else {
                data.put("priority", "LOW");
            }

            return data;

        }).toList();

        return ResponseEntity.ok(result);
    }
}
