# SMS Notification Implementation Guide

## Overview

The Flood Alert System now supports SMS notifications via multiple providers:
- **Dialog Axiata** (Local Sri Lankan provider - RECOMMENDED)
- **Mobitel** (Local Sri Lankan provider)
- **Twilio** (International provider - Fallback)

The system automatically implements failover - if one provider fails, it tries the next.

---

## ✅ SMS Features Implemented

1. **Alert Notifications via SMS**
   - Sent to users when floods are reported
   - Includes severity, location, distance, and water level
   - Complements email notifications

2. **Report Confirmation via SMS**
   - Confirms to reporter that flood was received
   - Shows alert count sent to nearby users

3. **Multi-Provider Support**
   - Automatic failover between providers
   - Priority-based provider selection
   - Graceful degradation if all fail

4. **Phone Number Flexibility**
   - Accepts multiple formats: +94XXXXXXXXX, 07XXXXXXXX, etc.
   - Automatic format normalization

---

## Setup Instructions

### Option 1: Using Twilio (International - Easiest to Set Up)

#### 1. Create Twilio Account
- Go to https://www.twilio.com/
- Sign up for a free account
- Verify your phone number
- Get your Account SID and Auth Token

#### 2. Purchase a Twilio Phone Number
- In Twilio Console, go to Phone Numbers → Manage Numbers
- Buy a number that supports SMS to Sri Lanka
- Or use trial credit to test

#### 3. Configure in application.properties

```properties
twilio.account-sid=ACxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
twilio.auth-token=your_auth_token
twilio.phone-number=+1234567890
twilio.enabled=true

# Also enable other providers if needed
# dialog.enabled=false
# mobitel.enabled=false
```

#### 4. Test Configuration

```bash
# Build and run application
mvn clean install
mvn spring-boot:run

# Check SMS provider status (via logs or API endpoint)
# Should see: "✓ Configured" for Twilio
```

---

### Option 2: Using Dialog Axiata (Local SL - RECOMMENDED)

#### 1. Register with Dialog
- Visit Dialog Axiata Business Portal: https://www.dialog.lk/
- Register for Business SMS service
- Create API credentials
- Get your:
  - API URL
  - API Key
  - Sender ID (e.g., "FLOODALERT")

#### 2. Configure in application.properties

```properties
dialog.api-url=https://api.dialog.lk/sms/send
dialog.api-key=your_api_key_from_dialog
dialog.sender-id=FLOODALERT
dialog.enabled=true

# Optional: also enable Twilio as fallback
# twilio.enabled=false
# mobitel.enabled=false
```

#### 3. Test Configuration

```bash
# Send test SMS
curl -X POST http://localhost:8080/api/notifications/test-sms \
  -H "Content-Type: application/json" \
  -d '{
    "phoneNumber": "+94771234567",
    "message": "Test SMS from Flood Alert System"
  }'
```

---

### Option 3: Using Mobitel (Local SL)

#### 1. Register with Mobitel
- Contact Mobitel Business SMS Service
- Phone: +94 (0) 112 680 777
- Email: sms@mobitel.lk
- Get your:
  - API URL
  - Username
  - Password
  - Sender ID (e.g., "ALERT")

#### 2. Configure in application.properties

```properties
mobitel.api-url=https://api.mobitel.lk/sms
mobitel.username=your_mobitel_username
mobitel.password=your_mobitel_password
mobitel.sender-id=ALERT
mobitel.enabled=true

# Optional: also enable Twilio as fallback
# twilio.enabled=false
# dialog.enabled=false
```

---

### Option 4: Using Multiple Providers with Failover

Configure all providers and let the system automatically failover:

```properties
# Dialog (Primary - Local SL)
dialog.api-url=https://api.dialog.lk/sms/send
dialog.api-key=your_dialog_key
dialog.sender-id=FLOODALERT
dialog.enabled=true

# Mobitel (Secondary - Local SL)
mobitel.api-url=https://api.mobitel.lk/sms
mobitel.username=mobitel_user
mobitel.password=mobitel_pass
mobitel.sender-id=ALERT
mobitel.enabled=true

# Twilio (Fallback - International)
twilio.account-sid=ACxxxxx
twilio.auth-token=xxxxx
twilio.phone-number=+1234567890
twilio.enabled=true
```

**Priority Order (Automatic):**
1. Dialog (tries first)
2. Mobitel (if Dialog fails)
3. Twilio (if both fail)

---

## Phone Number Formats

All SMS providers accept multiple phone number formats. The system automatically normalizes them:

| Format | Example | Normalized |
|--------|---------|------------|
| E.164 | +94771234567 | +94771234567 |
| Sri Lankan | 0771234567 | +94771234567 |
| International | 00941234567 | +94771234567 |

---

## SMS Message Examples

### Alert Notification SMS
```
🚨 FLOOD ALERT: CRITICAL in Downtown District (Distance: 2.1 km). Water level: 250 cm. Stay safe!
```

### Report Confirmation SMS
```
Flood report received for Downtown District (Level: 250 cm, Severity: CRITICAL). Alerts sent to nearby users. Stay safe!
```

---

## API Endpoints for SMS Testing

### Check Provider Status

```http
GET /api/notifications/sms-status
```

Response:
```json
{
  "providers": [
    "Dialog Axiata",
    "Mobitel",
    "Twilio"
  ],
  "configured": 3,
  "status": "All SMS providers configured. Failover enabled."
}
```

### Send Test SMS (Admin Only)

```http
POST /api/notifications/test-sms
Content-Type: application/json

{
  "phoneNumber": "+94771234567",
  "message": "Test SMS"
}
```

Response:
```json
{
  "success": true,
  "provider": "Dialog Axiata",
  "message": "SMS sent successfully"
}
```

---

## Cost Comparison (As of April 2024)

| Provider | Cost per SMS | Best For |
|----------|-------------|----------|
| **Dialog Axiata** | LKR 1-2 | Local SL - Cheapest |
| **Mobitel** | LKR 1-2 | Local SL - Alternative |
| **Twilio** | USD 0.0075-0.20 | International fallback |

---

## Testing Scenarios

### Scenario 1: Dialog Only
```properties
dialog.enabled=true
mobitel.enabled=false
twilio.enabled=false
```
**Result:** All SMS via Dialog

### Scenario 2: Dialog + Twilio Failover
```properties
dialog.enabled=true
mobitel.enabled=false
twilio.enabled=true
```
**Result:** 
- Primary: Dialog
- Fallback: Twilio

### Scenario 3: All Three with Full Failover
```properties
dialog.enabled=true
mobitel.enabled=true
twilio.enabled=true
```
**Result:**
- Primary: Dialog
- Secondary: Mobitel
- Fallback: Twilio

### Scenario 4: SMS Disabled (Email Only)
```properties
dialog.enabled=false
mobitel.enabled=false
twilio.enabled=false
```
**Result:** Only email notifications sent

---

## Troubleshooting

### SMS Not Sending

**Check 1: Provider Configuration**
```properties
# Verify provider is enabled
dialog.enabled=true

# Verify all required fields are set
dialog.api-url=...
dialog.api-key=...
dialog.sender-id=...
```

**Check 2: Phone Number Format**
```
Valid: +94771234567, 0771234567
Invalid: 94771234567 (missing + or 0)
```

**Check 3: SMS Service Status**
```bash
# Check logs
tail -f logs/application.log | grep "SMS\|Dialog\|Twilio"

# Should see:
# "SMS sent via Dialog to +94771234567"
# or
# "Failed to send SMS via Dialog: [error details]"
```

**Check 4: API Credentials**
```bash
# Test Dialog API manually
curl "https://api.dialog.lk/sms/send?key=YOUR_KEY&to=94771234567&text=Test&sender=ALERT"

# Test Twilio manually (if using)
# Use Twilio CLI or dashboard
```

### Common Errors

1. **"Provider not configured"**
   - Solution: Verify `enabled=true` and all required fields in application.properties

2. **"Invalid phone number format"**
   - Solution: Use +94 prefix or ensure number starts with 07

3. **"API Key invalid"**
   - Solution: Check API credentials with provider

4. **"All providers failed"**
   - Solution: Check internet connectivity and all provider credentials

---

## Implementation Details

### File Structure

```
src/main/java/Atomic5/demo/service/
├── sms/
│   ├── SmsProvider.java              # Interface
│   ├── TwilioSmsProvider.java         # Twilio implementation
│   ├── DialogSmsProvider.java         # Dialog implementation
│   ├── MobitelSmsProvider.java        # Mobitel implementation
│   └── SmsService.java                # Manager with failover
└── NotificationService.java           # Integrates SMS & Email
```

### Key Classes

1. **SmsProvider Interface**
   - Defines contract for all SMS providers
   - Methods: sendSms(), isConfigured(), getProviderName()

2. **SmsService Manager**
   - Handles routing and failover
   - Automatically tries providers in priority order
   - Provides provider status endpoint

3. **TwilioSmsProvider**
   - Uses Twilio SDK
   - Supports worldwide SMS

4. **DialogSmsProvider**
   - Uses HTTP GET requests
   - Local Sri Lankan provider

5. **MobitelSmsProvider**
   - Uses HTTP POST with JSON
   - Local Sri Lankan provider

6. **NotificationService**
   - Sends both email and SMS
   - Integrates with SmsService

---

## Production Recommendations

1. **Use Local Providers First**
   - Dialog or Mobitel for lower cost
   - Better reliability in Sri Lanka

2. **Enable Failover**
   - Configure at least 2 providers
   - Ensures alerts reach users even if one provider fails

3. **Monitor SMS Delivery**
   - Set up logging and monitoring
   - Track delivery rates and failures

4. **Rate Limiting**
   - Implement rate limiting for SMS
   - Prevent SMS spam/abuse

5. **Cost Control**
   - Monitor SMS volume and costs
   - Implement budget alerts

6. **User Opt-Out**
   - Allow users to disable SMS
   - Respect user preferences

---

## Future Enhancements

1. **SMS Delivery Receipts**
   - Track delivery status via provider callbacks

2. **SMS Templates**
   - Configurable message templates

3. **Two-Way SMS**
   - Allow users to respond via SMS

4. **Message Queuing**
   - Queue messages if provider is down

5. **Analytics**
   - Track SMS delivery rates and costs

6. **International Support**
   - Add more global SMS providers

---

## Support Resources

### Dialog Axiata
- Website: https://www.dialog.lk/
- Business SMS: https://www.dialog.lk/web/sms
- Documentation: https://www.dialog.lk/api

### Mobitel
- Website: https://www.mobitel.lk/
- SMS Service: https://www.mobitel.lk/business
- Contact: sms@mobitel.lk

### Twilio
- Website: https://www.twilio.com/
- Documentation: https://www.twilio.com/docs/sms
- Console: https://www.twilio.com/console

---

## Status: ✅ COMPLETE

SMS notifications are fully implemented and ready for use with:
- ✅ Multiple provider support
- ✅ Automatic failover
- ✅ Flexible phone number formats
- ✅ Integration with email
- ✅ Configuration examples
- ✅ Error handling
- ✅ Logging

Choose your provider and configure in application.properties!
