package Atomic5.demo.repository;

import Atomic5.demo.model.FloodReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FloodReportRepository extends JpaRepository<FloodReport, Long> {

    @Query("SELECT f FROM FloodReport f WHERE f.expiryTime > :now ORDER BY f.reportTime DESC")
    List<FloodReport> findActiveReports(@Param("now") LocalDateTime now);

    @Query("SELECT f FROM FloodReport f WHERE " +
            "f.latitude BETWEEN :minLat AND :maxLat AND " +
            "f.longitude BETWEEN :minLon AND :maxLon AND " +
            "f.expiryTime > :now")
    List<FloodReport> findReportsInArea(
            @Param("minLat") Double minLat,
            @Param("maxLat") Double maxLat,
            @Param("minLon") Double minLon,
            @Param("maxLon") Double maxLon,
            @Param("now") LocalDateTime now
    );
}