package Atomic5.demo.repository;

import Atomic5.demo.model.Alert;
import Atomic5.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findByRecipientOrderByCreatedAtDesc(User recipient);

    @Query("SELECT COUNT(a) FROM Alert a WHERE a.recipient = :user AND a.status = 'UNREAD'")
    long countUnreadAlerts(@Param("user") User user);
}
