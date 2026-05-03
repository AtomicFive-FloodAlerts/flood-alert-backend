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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = context.getAuthentication();

            String email = null;
            if (authentication != null && authentication.isAuthenticated()) {
                email = authentication.getName();
            }

            if (email == null || email.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("User not authenticated");
            }

            User reporter = userRepository.findByEmail(email);

            if (reporter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("User not found");
            }

            FloodReport report = new FloodReport();
            report.setReportedBy(reporter);
            report.setAreaName(reportDTO.getAreaName());
            report.setLatitude(reportDTO.getLatitude());
            report.setLongitude(reportDTO.getLongitude());
            report.setWaterLevel(reportDTO.getWaterLevel());
            report.setDescription(reportDTO.getDescription());

            FloodSeverity severity = floodSeverityService.calculateSeverityFromWaterLevel(
                    reportDTO.getWaterLevel());
            report.setSeverity(severity);

            FloodReport savedReport = floodReportRepository.save(report);

            alertService.generateAlertsForFloodReport(savedReport);

            // Send confirmation email to reporter
            notificationService.sendFloodReportConfirmation(savedReport, reporter);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Flood report created and alerts generated");
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
}