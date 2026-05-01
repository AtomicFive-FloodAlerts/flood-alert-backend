package Atomic5.demo.repository;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.AlertStatus;
import Atomic5.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByRecipientOrderByCreatedAtDesc(User recipient);

    @Query("SELECT COUNT(a) FROM Alert a WHERE a.recipient = :user AND a.status = 'UNREAD'")
    long countUnreadAlerts(@Param("user") User user);

    @Query("SELECT a FROM Alert a WHERE a.recipient = :user AND a.status = :status ORDER BY a.createdAt DESC")
    List<Alert> findByRecipientAndStatusOrderByCreatedAtDesc(@Param("user") User user, @Param("status") AlertStatus status);

    @Query("SELECT a FROM Alert a WHERE a.recipient = :user AND a.status IN ('UNREAD', 'ACKNOWLEDGED') ORDER BY a.createdAt DESC")
    List<Alert> findActiveAlertsForUser(@Param("user") User user);

    @Query("SELECT a FROM Alert a WHERE a.recipient = :user AND a.createdAt > :since ORDER BY a.createdAt DESC")
    List<Alert> findAlertsCreatedAfter(@Param("user") User user, @Param("since") LocalDateTime since);

    @Query("SELECT a FROM Alert a WHERE a.recipient = :user AND a.distanceKm <= :radiusKm ORDER BY a.createdAt DESC")
    List<Alert> findAlertsWithinRadius(@Param("user") User user, @Param("radiusKm") Double radiusKm);
}
