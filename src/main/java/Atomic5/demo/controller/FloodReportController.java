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
import java.util.List;

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

        // Validate input first (VERY IMPORTANT)
        if (reportDTO.getReportedById() == null) {
            return ResponseEntity.badRequest().body("reportedById is required");
        }

        try {
            // Find user safely
            User reporter = userRepository.findById(reportDTO.getReportedById())
                    .orElse(null);

            if (reporter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found");
            }

            // Create flood report
            FloodReport report = new FloodReport(
                    reporter,
                    reportDTO.getLatitude(),
                    reportDTO.getLongitude(),
                    reportDTO.getDescription(),
                    reportDTO.getWaterLevel(),
                    reportDTO.getAreaName()
            );

            // Calculate severity
            FloodSeverity severity =
                    floodSeverityService.calculateSeverityFromWaterLevel(
                            reportDTO.getWaterLevel()
                    );

            report.setSeverity(severity);

            // Save
            FloodReport savedReport = floodReportRepository.save(report);

            // Generate alerts
            alertService.generateAlertsForFloodReport(savedReport);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedReport); // 🔥 return object (better than string)

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error creating flood report: " + e.getMessage());
        }
    }

    /**
     * Get all active flood reports
     */
    @GetMapping("/active")
    public ResponseEntity<List<FloodReport>> getActiveFloods() {
        List<FloodReport> activeReports =
                floodReportRepository.findActiveReports(LocalDateTime.now());

        return ResponseEntity.ok(activeReports);
    }

    /**
     * Get flood reports in a specific area
     */
    @GetMapping("/area")
    public ResponseEntity<List<FloodReport>> getFloodsInArea(
            @RequestParam Double minLat,
            @RequestParam Double maxLat,
            @RequestParam Double minLon,
            @RequestParam Double maxLon) {

        List<FloodReport> reports =
                floodReportRepository.findReportsInArea(
                        minLat, maxLat, minLon, maxLon, LocalDateTime.now()
                );

        return ResponseEntity.ok(reports);
    }

    /**
     * Get flood by ID
     */
    @GetMapping("/{floodId}")
    public ResponseEntity<FloodReport> getFloodById(@PathVariable Long floodId) {
        return floodReportRepository.findById(floodId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}