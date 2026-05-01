# Flood Alert System - Complete Documentation

## System Overview

The Flood Alert System is a real-time alert generation and notification system that automatically sends alerts to nearby users when a flood is reported. The system uses sophisticated location-based calculations and severity-based alert radiuses to ensure users receive timely and relevant information.

---

## Architecture & Components

### 1. **Flood Severity Calculation**

#### Model: `FloodSeverity.java`

- **Enum Values:**
  - `LOW` (0-30 cm): Minor flooding
  - `MODERATE` (31-100 cm): Moderate flooding
  - `HIGH` (101-200 cm): Severe flooding
  - `CRITICAL` (201+ cm): Extreme flooding

#### Service: `FloodSeverityService.java`

Methods:

- `calculateSeverityFromWaterLevel(int waterLevel)`: Returns severity enum based on water level
- `calculateSeverityScore(FloodReport report)`: Returns 0-100 severity score for visualization
- `getAlertRadiusKm(FloodSeverity severity)`: Returns alert radius in kilometers
  - LOW → 2 km
  - MODERATE → 5 km
  - HIGH → 10 km
  - CRITICAL → 15 km
- `requiresImmediateAction(FloodReport report)`: Returns true for HIGH and CRITICAL

**Example:**

```java
int waterLevel = 150; // cm
FloodSeverity severity = floodSeverityService.calculateSeverityFromWaterLevel(waterLevel);
// Result: FloodSeverity.HIGH

double alertRadius = floodSeverityService.getAlertRadiusKm(severity);
// Result: 10.0 km
```

---

### 2. **Location-Based Alert Radius**

#### Utility: `LocationUtil.java`

**Haversine Formula Implementation** for accurate distance calculation between two geographical points.

Methods:

- `calculateDistance(lat1, lon1, lat2, lon2)`: Calculates distance in kilometers
- `isWithinRadius(userLat, userLon, floodLat, floodLon, radiusKm)`: Checks if user is within alert radius

**Example:**

```java
// User location
double userLat = 40.7128, userLon = -74.0060;

// Flood location
double floodLat = 40.7150, floodLon = -74.0070;

// Calculate distance
double distance = LocationUtil.calculateDistance(userLat, userLon, floodLat, floodLon);
// Result: ~0.3 km

// Check if within alert radius (5 km)
boolean withinRadius = LocationUtil.isWithinRadius(userLat, userLon, floodLat, floodLon, 5.0);
// Result: true
```

---

### 3. **Alert Generation**

#### Service: `AlertService.java`

**Key Method:** `generateAlertsForFloodReport(FloodReport floodReport)`

**Process:**

1. Calculates alert radius based on flood severity
2. Retrieves all active users from database
3. For each user:
   - Skips if user is the reporter
   - Skips if user has notifications disabled
   - Calculates distance to flood location
   - If within alert radius, creates and saves alert
   - Sends email notification via `NotificationService`

**Alert Status Workflow:**

```
UNREAD → READ → (ACKNOWLEDGED or DISMISSED)
```

**Status Meanings:**

- `UNREAD`: User hasn't seen the alert
- `READ`: User has viewed the alert
- `ACKNOWLEDGED`: User has acknowledged receiving the alert
- `DISMISSED`: User dismissed the alert

---

### 4. **Notification Logic**

#### Service: `NotificationService.java`

**Email Notifications:**

1. **Alert Notification Email:**
   - Sends HTML-formatted email with alert details
   - Includes severity level with color coding
   - Shows distance from user to flood location
   - Provides safety tips
   - Method: `sendAlertNotification(Alert alert, User recipient)`

2. **Report Confirmation Email:**
   - Sent to reporter after flood report submission
   - Confirms report reception and alert generation
   - Method: `sendFloodReportConfirmation(FloodReport report, User reporter)`

3. **SMS Notifications (Future):**
   - Placeholder for SMS implementation
   - Would use Twilio or similar service
   - Method: `sendSmsNotification(User recipient, String message)`

**Configuration Required (in `application.properties`):**

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

---

## API Endpoints

### Flood Report Endpoints

#### 1. Report a New Flood

```http
POST /api/floods/report
Content-Type: application/json

{
  "reportedById": 1,
  "latitude": 40.7128,
  "longitude": -74.0060,
  "description": "Severe flooding in downtown area",
  "waterLevel": 180,
  "areaName": "Downtown District"
}
```

**Response (201 Created):**

```json
"Flood report created and alerts generated"
```

**Actions:**

- Saves flood report to database
- Calculates severity based on water level
- Generates alerts for all nearby users
- Sends confirmation email to reporter
- Sends alert emails to affected users

---

#### 2. Get Active Flood Reports

```http
GET /api/floods/active
```

**Response:**

```json
[
  {
    "id": 1,
    "areaName": "Downtown District",
    "latitude": 40.7128,
    "longitude": -74.006,
    "severity": "HIGH",
    "waterLevel": 180,
    "reportTime": "2024-04-26T10:30:00",
    "expiryTime": "2024-04-26T16:30:00"
  }
]
```

---

#### 3. Get Floods in Specific Area

```http
GET /api/floods/area?minLat=40.70&maxLat=40.72&minLon=-74.01&maxLon=-74.00
```

**Response:** List of flood reports within bounding box

---

### Alert Endpoints

#### 1. Get All Alerts for User

```http
GET /api/alerts/user/{userId}
```

**Response:**

```json
[
  {
    "id": 1,
    "title": "Flood Alert - HIGH",
    "message": "⚠️ HIGH reported near Downtown District at distance 2.5 km. Severe flooding...",
    "status": "UNREAD",
    "severity": "HIGH",
    "distanceKm": 2.5,
    "areaName": "Downtown District",
    "createdAt": "2024-04-26T10:30:00"
  }
]
```

---

#### 2. Get Unread Alert Count

```http
GET /api/alerts/user/{userId}/unread-count
```

**Response:**

```json
3
```

---

#### 3. Mark Alert as Read

```http
PUT /api/alerts/{alertId}/read
```

**Response:** Updated alert object with status "READ"

---

#### 4. Acknowledge Alert

```http
PUT /api/alerts/{alertId}/acknowledge
```

**Response:** Updated alert object with status "ACKNOWLEDGED"

---

#### 5. Dismiss Alert

```http
PUT /api/alerts/{alertId}/dismiss
```

**Response:** Updated alert object with status "DISMISSED"

---

#### 6. Delete Alert

```http
DELETE /api/alerts/{alertId}
```

**Response:** 204 No Content

---

#### 7. Get Alerts by Severity

```http
GET /api/alerts/user/{userId}/severity/{severity}
```

**Severity Options:** `LOW`, `MODERATE`, `HIGH`, `CRITICAL`

---

#### 8. Get Active Alerts (UNREAD or ACKNOWLEDGED)

```http
GET /api/alerts/user/{userId}/active
```

---

## Data Models

### Alert Model

```java
@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flood_report_id")
    private FloodReport floodReport;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User recipient;

    private String title;           // Alert title
    private String message;         // Alert message

    @Enumerated(EnumType.STRING)
    private AlertStatus status;     // UNREAD, READ, ACKNOWLEDGED, DISMISSED

    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    private Double distanceKm;      // Distance from user to flood
}
```

### FloodReport Model

```java
@Entity
@Table(name = "flood_reports")
public class FloodReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reportedBy;

    private Double latitude;
    private Double longitude;
    private String description;

    @Enumerated(EnumType.STRING)
    private FloodSeverity severity;

    private LocalDateTime reportTime;
    private LocalDateTime expiryTime;

    private Integer waterLevel;     // in cm
    private String areaName;
}
```

### User Model

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phoneNumber;

    private Double latitude;
    private Double longitude;

    private Boolean notificationsEnabled;
}
```

---

## Alert Radius Decision Logic

The alert radius is dynamically determined based on flood severity to ensure:

- **Low-impact floods** have smaller alert radiuses (2 km) to avoid unnecessary notifications
- **Critical floods** have larger alert radiuses (15 km) to reach as many people as possible
- Users within the calculated radius receive real-time notifications

**Radius Assignment:**
| Severity | Water Level | Radius | Impact |
|----------|------------|--------|--------|
| LOW | 0-30 cm | 2 km | Local area only |
| MODERATE | 31-100 cm | 5 km | District level |
| HIGH | 101-200 cm | 10 km | City level |
| CRITICAL | 201+ cm | 15 km | Regional level |

---

## Database Queries

### AlertRepository Methods

- `findByRecipientOrderByCreatedAtDesc(User)`: Get all alerts for user
- `countUnreadAlerts(User)`: Count unread alerts
- `findByRecipientAndStatusOrderByCreatedAtDesc()`: Get alerts by status
- `findActiveAlertsForUser()`: Get UNREAD or ACKNOWLEDGED alerts
- `findAlertsCreatedAfter()`: Get alerts created after specific time
- `findAlertsWithinRadius()`: Get alerts within specific distance

### FloodReportRepository Methods

- `findActiveReports()`: Get non-expired flood reports
- `findReportsInArea()`: Get reports within bounding box
- `findBySeverity()`: Get reports by severity level
- `findByAreaName()`: Get reports by area name
- `findByReportedBy()`: Get reports from specific user
- `findCriticalReports()`: Get HIGH or CRITICAL reports

---

## Transaction Management

All critical operations use `@Transactional` to ensure data consistency:

- Alert generation ensures all alerts are created atomically
- Status updates are atomic operations
- Report creation and alert generation happen as a single transaction

---

## Error Handling

The system gracefully handles:

- Missing users: Returns HTTP 404
- Invalid coordinates: Skipped during distance calculation
- Email failures: Logged as warnings, doesn't block alert creation
- Database errors: Returned as HTTP 400 with error message

---

## Testing Scenarios

### Scenario 1: Low Severity Flood

```
Water Level: 20 cm → Severity: LOW
Alert Radius: 2 km
Users within 2 km: Receive alerts
```

### Scenario 2: Critical Flood

```
Water Level: 250 cm → Severity: CRITICAL
Alert Radius: 15 km
Users within 15 km: Receive alerts with urgency indicators
```

### Scenario 3: User Notification Disabled

```
User has notificationsEnabled = false
Even if within alert radius: No alert is created
```

### Scenario 4: Reporter Exclusion

```
User who reports flood: Excluded from receiving alert about their own report
```

---

## Performance Optimization Tips

1. **Database Indexing:**
   - Index on `User.latitude`, `User.longitude` for distance calculations
   - Index on `Alert.recipient_id`, `Alert.status` for common queries
   - Index on `FloodReport.expiryTime` for active reports

2. **Alert Generation:**
   - Use batch processing for large user bases
   - Implement caching for severity-to-radius mapping
   - Use async email sending to avoid blocking

3. **Query Optimization:**
   - Filter by expiryTime in database queries
   - Use pagination for listing endpoints
   - Cache active flood reports

---

## Future Enhancements

1. **SMS Notifications** via Twilio integration
2. **Push Notifications** for mobile apps
3. **Alert History Analytics** for reporting
4. **Machine Learning** for severity prediction
5. **WebSocket Real-time Updates** for live alerts
6. **Geofencing** using Geo-spatial databases
7. **Multi-language Support** for alerts
8. **Alert Acknowledgment Tracking** for authorities

---

## Configuration Examples

### Application Properties

```properties
# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=floods@company.com
spring.mail.password=app-specific-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/flood_alerts
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

# Application
server.port=8080
server.servlet.context-path=/api
```

---

## Troubleshooting

**Issue:** Alerts not being sent

- Check email configuration in application.properties
- Verify user email addresses are valid
- Check if user has `notificationsEnabled = true`
- Check database logs for alert creation

**Issue:** Wrong users receiving alerts

- Verify latitude/longitude coordinates
- Check alert radius configuration
- Verify user location data in database

**Issue:** Email delivery delays

- Use async email sending
- Check email server configuration
- Monitor email queue

---

## Support & Contact

For questions or issues related to the Flood Alert System, please contact the development team or file an issue in the project repository.
