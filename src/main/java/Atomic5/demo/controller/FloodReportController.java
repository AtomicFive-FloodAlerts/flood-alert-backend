package Atomic5.demo.controller;

import Atomic5.demo.dto.FloodReportDTO;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.FloodSeverity;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.FloodReportRepository;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.service.AlertService;
import Atomic5.demo.service.FloodSeverityService;
import Atomic5.demo.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/floods")
@CrossOrigin(origins = "*")
public class FloodReportController {

    private final FloodReportRepository floodReportRepository;
    private final UserRepository userRepository;
    private final AlertService alertService;
    private final FloodSeverityService floodSeverityService;
    private final NotificationService notificationService;

    public FloodReportController(FloodReportRepository floodReportRepository,
            UserRepository userRepository,
            AlertService alertService,
            FloodSeverityService floodSeverityService,
            NotificationService notificationService) {
        this.floodReportRepository = floodReportRepository;
        this.userRepository = userRepository;
        this.alertService = alertService;
        this.floodSeverityService = floodSeverityService;
        this.notificationService = notificationService;
    }

    @PostMapping("/report")
        public ResponseEntity<?> reportFlood(@RequestBody FloodReportDTO reportDTO) {
        try {

                // Validate required fields
                if (reportDTO.getLatitude() == null ||
                reportDTO.getLongitude() == null ||
                reportDTO.getWaterLevel() == null ||
                reportDTO.getAreaName() == null) {

                return ResponseEntity.badRequest()
                        .body("Missing required fields");
                }

                // Get logged-in user
                String email = SecurityContextHolder.getContext().getAuthentication().getName();
                User reporter = userRepository.findByEmail(email);

                if (reporter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found");
                }

                // Create report
                FloodReport report = new FloodReport(
                        reporter.getId(),
                        reportDTO.getLatitude(),
                        reportDTO.getLongitude(),
                        reportDTO.getDescription(),
                        reportDTO.getWaterLevel(),
                        reportDTO.getAreaName()
                );

                // Calculate severity
                FloodSeverity severity = floodSeverityService
                        .calculateSeverityFromWaterLevel(reportDTO.getWaterLevel());
                report.setSeverity(severity);

                // Save report
                FloodReport savedReport = floodReportRepository.save(report);

                // Generate alerts
                alertService.generateAlertsForFloodReport(savedReport);

                // Send notification
                notificationService.sendFloodReportConfirmation(savedReport, reporter);

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(savedReport);

        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error creating flood report: " + e.getMessage());
        }
        }
        @GetMapping("/active")
        public ResponseEntity<List<FloodReport>> getActiveFloods() {
                return ResponseEntity.ok(floodReportRepository.findAll());
        }

        @GetMapping("/area")
        public ResponseEntity<List<FloodReport>> getFloodsInArea(
                @RequestParam Double minLat,
                @RequestParam Double maxLat,
                @RequestParam Double minLon,
                @RequestParam Double maxLon) {
                return ResponseEntity.ok(floodReportRepository.findAll());
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
