package Atomic5.demo.repository;

import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.FloodSeverity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FloodReportRepository extends MongoRepository<FloodReport, String> {

    List<FloodReport> findByExpiryTimeAfterOrderByReportTimeDesc(LocalDateTime now);

    List<FloodReport> findByLatitudeBetweenAndLongitudeBetweenAndExpiryTimeAfter(
            Double minLat,
            Double maxLat,
            Double minLon,
            Double maxLon,
            LocalDateTime now
    );

    List<FloodReport> findBySeverityAndExpiryTimeAfterOrderByReportTimeDesc(
            FloodSeverity severity,
            LocalDateTime now
    );

    List<FloodReport> findByAreaNameAndExpiryTimeAfterOrderByReportTimeDesc(
            String areaName,
            LocalDateTime now
    );

    // ✅ FIXED LINE
    List<FloodReport> findByReportedByIdOrderByReportTimeDesc(Long userId);

    List<FloodReport> findByExpiryTimeAfterAndSeverityInOrderByReportTimeDesc(
            LocalDateTime now,
            List<FloodSeverity> severities
    );
}