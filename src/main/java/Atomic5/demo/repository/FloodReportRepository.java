package Atomic5.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Atomic5.demo.model.FloodReport;
import Atomic5.demo.model.FloodSeverity;

@Repository
public interface FloodReportRepository extends JpaRepository<FloodReport, Long> {

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

    List<FloodReport> findByReportedByIdOrderByReportTimeDesc(Long userId);

    List<FloodReport> findByExpiryTimeAfterAndSeverityInOrderByReportTimeDesc(
            LocalDateTime now,
            List<FloodSeverity> severities
    );
}