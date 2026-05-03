package Atomic5.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Atomic5.demo.dto.FloodReportDTO;
import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.FloodSeverity;
import Atomic5.demo.model.User;
import Atomic5.demo.repository.FloodReportRepository;
import Atomic5.demo.repository.UserRepository;
import Atomic5.demo.service.AlertService;
import Atomic5.demo.service.FloodSeverityService;
import Atomic5.demo.service.NotificationService;

@RestController
@RequestMapping("/api/floods")
@CrossOrigin(origins = "*")
public class FloodReportController {

    private final FloodReportRepository floodReportRepository;
    private final UserRepository userRepository;
    private final AlertService alertService;
    private final FloodSeverityService floodSeverityService;
    private final NotificationService notificationService;

    public FloodReportController(
        FloodReportRepository floodReportRepository,
        UserRepository userRepository,
        AlertService alertService,
        FloodSeverityService floodSeverityService,
        NotificationService notificationService
    ) {
        this.floodReportRepository = floodReportRepository;
        this.userRepository = userRepository;
        this.alertService = alertService;
        this.floodSeverityService = floodSeverityService;
        this.notificationService = notificationService;
    }

    @PostMapping("/report")
    public ResponseEntity<?> reportFlood(@RequestBody FloodReportDTO reportDTO) {

        try {
            User reporter = userRepository
                    .findById(reportDTO.getReportedById())
                    .orElse(null);

            if (reporter == null) {
                return ResponseEntity.badRequest().body("Invalid user");
            }

            FloodReport report = new FloodReport();

            Double latitude = reportDTO.getLatitude();
            Double longitude = reportDTO.getLongitude();

            report.setReportedById(reporter.getId());
            report.setLatitude(latitude);
            report.setLongitude(longitude);
            report.setDescription(reportDTO.getDescription());
            report.setWaterLevel(reportDTO.getWaterLevel().doubleValue());
            report.setAreaName(reportDTO.getAreaName());

            report.setReportTime(LocalDateTime.now());
            report.setExpiryTime(LocalDateTime.now().plusHours(6));

            FloodSeverity severity =
                    floodSeverityService.calculateSeverityFromWaterLevel(
                            reportDTO.getWaterLevel().doubleValue()
                    );

            report.setSeverity(severity);

            FloodReport savedReport = floodReportRepository.save(report);

            alertService.generateAlertsForFloodReport(savedReport);
            notificationService.sendFloodReportConfirmation(savedReport, reporter);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedReport);

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

            if (severity == null) {
                data.put("priority", "LOW");
            } else {
                switch (severity) {
                    case HIGH:
                    case CRITICAL:
                        data.put("priority", "HIGH");
                        break;
                    case MODERATE:
                        data.put("priority", "MEDIUM");
                        break;
                    case LOW:
                    default:
                        data.put("priority", "LOW");
                        break;
                }
            }

            return data;
        }).toList();

        return ResponseEntity.ok(result);
    }
}