# SMS Notification Quick Start Guide

## ✅ What's Implemented

The Flood Alert System now supports **SMS notifications** via multiple providers:

1. **Dialog Axiata** (Local Sri Lankan - RECOMMENDED)
2. **Mobitel** (Local Sri Lankan)
3. **Twilio** (International - Fallback)

### SMS Features

- ✅ Alert notifications to nearby users
- ✅ Report confirmation to flood reporters
- ✅ Automatic failover between providers
- ✅ Multiple phone number format support
- ✅ REST API endpoints for testing
- ✅ Provider status monitoring
- ✅ Comprehensive logging

---

## 🚀 Quick Start (3 Steps)

### Step 1: Choose Your SMS Provider

#### Option A: Dialog Axiata (Local - Best for Sri Lanka)

```properties
# In application.properties
dialog.api-url=https://api.dialog.lk/sms/send
dialog.api-key=YOUR_API_KEY_HERE
dialog.sender-id=FLOODALERT
dialog.enabled=true
```

#### Option B: Mobitel (Local - Alternative)

```properties
# In application.properties
mobitel.api-url=https://api.mobitel.lk/sms
mobitel.username=YOUR_USERNAME
mobitel.password=YOUR_PASSWORD
mobitel.sender-id=ALERT
mobitel.enabled=true
```

#### Option C: Twilio (International - Worldwide)

```properties
# In application.properties
twilio.account-sid=ACxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
twilio.auth-token=your_auth_token
twilio.phone-number=+1234567890
twilio.enabled=true
```

### Step 2: Test Configuration

```bash
# Check if SMS provider is configured
curl http://localhost:8080/api/notifications/sms-status

# Response example:
# {
#   "availableProviders": ["Dialog Axiata", "Mobitel"],
#   "isConfigured": true
# }
```

### Step 3: Send Test SMS

```bash
curl -X POST http://localhost:8080/api/notifications/test-sms \
  -H "Content-Type: application/json" \
  -d '{
    "phoneNumber": "+94771234567",
    "message": "Flood Alert System - Test SMS"
  }'

# Response:
# {
#   "success": true,
#   "phoneNumber": "*4567",
#   "message": "SMS sent successfully"
# }
```

---

## 📋 Provider Details

### Dialog Axiata (Recommended for Sri Lanka)

**Advantages:**

- Local provider (fastest, most reliable in SL)
- Cheapest option (LKR 1-2 per SMS)
- No need for external accounts
- Supports local numbers

**Setup:**

1. Contact Dialog Business: https://www.dialog.lk/
2. Register for SMS API service
3. Get API key and sender ID
4. Configure in application.properties

**Phone Number Format:** +94771234567

---

### Mobitel (Alternative Local Provider)

**Advantages:**

- Local provider
- Competitive pricing
- Good coverage

**Setup:**

1. Contact Mobitel SMS: sms@mobitel.lk
2. Register for SMS API
3. Get credentials
4. Configure in application.properties

**Phone Number Format:** +94771234567

---

### Twilio (International Fallback)

**Advantages:**

- Works worldwide
- Easy setup
- Automatic failover
- Reliable

**Setup:**

1. Create account: https://www.twilio.com/
2. Verify phone number
3. Buy SMS-capable number
4. Get Account SID and Auth Token
5. Configure in application.properties

**Phone Number Format:** +1234567890 (Twilio's number)

---

## 📱 How SMS Alerts Work

```
1. User reports flood
   ↓
2. System calculates severity & alert radius
   ↓
3. For each nearby user:
   ├─ Send EMAIL alert
   └─ Send SMS alert
      ├─ Try Dialog first
      ├─ If Dialog fails, try Mobitel
      └─ If Mobitel fails, try Twilio
   ↓
4. User receives alert via email and/or SMS
```

---

## 💬 SMS Message Examples

### Alert SMS

```
🚨 FLOOD ALERT: HIGH in Downtown (Distance: 2.5 km). Water level: 150 cm. Stay safe!
```

### Confirmation SMS

```
Flood report received for Downtown (Level: 150 cm, Severity: HIGH). Alerts sent to nearby users. Stay safe!
```

---

## 🔧 Configuration Template

```properties
# ============================================================
# SMS CONFIGURATION - Choose one or more providers
# ============================================================

# DIALOG AXIATA (Local SL - Recommended)
dialog.api-url=https://api.dialog.lk/sms/send
dialog.api-key=your_dialog_api_key
dialog.sender-id=FLOODALERT
dialog.enabled=true

# MOBITEL (Local SL - Optional)
mobitel.api-url=https://api.mobitel.lk/sms
mobitel.username=your_mobitel_username
mobitel.password=your_mobitel_password
mobitel.sender-id=ALERT
mobitel.enabled=false

# TWILIO (International - Optional Fallback)
twilio.account-sid=ACxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
twilio.auth-token=your_auth_token
twilio.phone-number=+1234567890
twilio.enabled=false

# ============================================================
# EMAIL CONFIGURATION (Already set up)
# ============================================================
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password

# ============================================================
# APPLICATION
# ============================================================
server.port=8080
logging.level.Atomic5.demo.service.sms=DEBUG
```

---

## 🧪 Testing Examples

### Test 1: Send Alert with SMS

```bash
# First, create a test user
POST http://localhost:8080/api/users
{
  "name": "Test User",
  "email": "user@test.com",
  "phoneNumber": "+94771234567",
  "latitude": 40.7128,
  "longitude": -74.0060,
  "notificationsEnabled": true
}

# Then report a flood
POST http://localhost:8080/api/floods/report
{
  "reportedById": 1,
  "latitude": 40.7128,
  "longitude": -74.0060,
  "description": "Test flood",
  "waterLevel": 150,
  "areaName": "Test Area"
}

# User receives both EMAIL and SMS alerts automatically!
```

### Test 2: Check Provider Status

```bash
GET http://localhost:8080/api/notifications/sms-status

# Example response:
{
  "status": "SMS Provider Status:\n- Dialog Axiata: ✓ Configured\n- Mobitel: ✗ Not Configured\n- Twilio: ✓ Configured\n",
  "availableProviders": ["Dialog Axiata", "Twilio"],
  "isConfigured": true
}
```

### Test 3: Send Direct SMS

```bash
POST http://localhost:8080/api/notifications/test-sms
{
  "phoneNumber": "+94771234567",
  "message": "Test message from Flood Alert System"
}

# System tries:
# 1. Dialog (succeeds) → Returns success
# OR
# 2. Mobitel (succeeds) → Returns success
# OR
# 3. Twilio (succeeds) → Returns success
```

---

## 🔍 Troubleshooting

### SMS Not Sending

**Check 1: Provider Configuration**

```bash
curl http://localhost:8080/api/notifications/sms-status
# Should show at least one "✓ Configured"
```

**Check 2: Phone Number Format**

```
Correct:  +94771234567  or  0771234567
Wrong:    94771234567   or  1234567
```

**Check 3: API Credentials**

- Dialog: Test API key on Dialog console
- Mobitel: Verify credentials with Mobitel
- Twilio: Check Account SID and token in Twilio console

**Check 4: Logs**

```bash
# View SMS logs
tail -f logs/application.log | grep "SMS\|Dialog\|Mobitel\|Twilio"
```

---

## 📊 SMS Costs Comparison

| Provider    | Cost         | Best For             |
| ----------- | ------------ | -------------------- |
| **Dialog**  | LKR 1-2      | Cheap local option   |
| **Mobitel** | LKR 1-2      | Cheap local option   |
| **Twilio**  | $0.0075-0.20 | International/Backup |

**Recommendation:** Use Dialog for primary, configure Twilio as fallback.

---

## 🏗️ File Structure

```
src/main/java/Atomic5/demo/service/sms/
├── SmsProvider.java          # Interface for all providers
├── DialogSmsProvider.java    # Dialog Axiata implementation
├── MobitelSmsProvider.java   # Mobitel implementation
├── TwilioSmsProvider.java    # Twilio implementation
└── SmsService.java           # Manager with failover logic

src/main/java/Atomic5/demo/controller/
└── NotificationController.java # API endpoints for testing

src/main/java/Atomic5/demo/service/
└── NotificationService.java  # Integrates email + SMS
```

---

## 🎯 Next Steps

1. **Choose a provider** (Dialog recommended for Sri Lanka)
2. **Get API credentials** from your chosen provider
3. **Configure in application.properties**
4. **Test with SMS test endpoint**
5. **Monitor SMS delivery** in logs

---

## 📞 Provider Contact Info

**Dialog Axiata**

- Website: https://www.dialog.lk/
- Business: https://www.dialog.lk/web/sms
- Support: +94 11 2680 777

**Mobitel**

- Website: https://www.mobitel.lk/
- SMS Support: sms@mobitel.lk
- Phone: +94 (0) 112 680 777

**Twilio**

- Website: https://www.twilio.com/
- Console: https://www.twilio.com/console
- Docs: https://www.twilio.com/docs/sms

---

## ✨ Status

✅ **SMS Implementation Complete**

- Multi-provider support
- Automatic failover
- Easy configuration
- Production ready

**Ready to use!** Choose your provider and configure in application.properties.

---

## 📚 For More Information

- Full guide: See `SMS_IMPLEMENTATION_GUIDE.md`
- Alert system: See `ALERT_SYSTEM_DOCUMENTATION.md`
- Configuration: See `SMS_CONFIGURATION_GUIDE.properties`
