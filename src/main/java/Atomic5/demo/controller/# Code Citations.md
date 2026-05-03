# Code Citations

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/visualkhh/lib-spring/blob/89e7d4b9a6318138d24f81e4e0fc2646f2d6a3d5/boot/security-jpa-thymeleaf/src/main/java/com/today/house/controller/AnonsController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/qasanov/pay-day-bank/blob/28efcd9cacb75c9b07a55634f07b6414dc34a473/pbd-ms-account/src/main/java/com/paydaybank/ms/account/web/AccountController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/visualkhh/lib-spring/blob/89e7d4b9a6318138d24f81e4e0fc2646f2d6a3d5/boot/security-jpa-thymeleaf/src/main/java/com/today/house/controller/AnonsController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/qasanov/pay-day-bank/blob/28efcd9cacb75c9b07a55634f07b6414dc34a473/pbd-ms-account/src/main/java/com/paydaybank/ms/account/web/AccountController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/visualkhh/lib-spring/blob/89e7d4b9a6318138d24f81e4e0fc2646f2d6a3d5/boot/security-jpa-thymeleaf/src/main/java/com/today/house/controller/AnonsController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/qasanov/pay-day-bank/blob/28efcd9cacb75c9b07a55634f07b6414dc34a473/pbd-ms-account/src/main/java/com/paydaybank/ms/account/web/AccountController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/visualkhh/lib-spring/blob/89e7d4b9a6318138d24f81e4e0fc2646f2d6a3d5/boot/security-jpa-thymeleaf/src/main/java/com/today/house/controller/AnonsController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/qasanov/pay-day-bank/blob/28efcd9cacb75c9b07a55634f07b6414dc34a473/pbd-ms-account/src/main/java/com/paydaybank/ms/account/web/AccountController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/visualkhh/lib-spring/blob/89e7d4b9a6318138d24f81e4e0fc2646f2d6a3d5/boot/security-jpa-thymeleaf/src/main/java/com/today/house/controller/AnonsController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/qasanov/pay-day-bank/blob/28efcd9cacb75c9b07a55634f07b6414dc34a473/pbd-ms-account/src/main/java/com/paydaybank/ms/account/web/AccountController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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
`````

## License: unknown

https://github.com/visualkhh/lib-spring/blob/89e7d4b9a6318138d24f81e4e0fc2646f2d6a3d5/boot/security-jpa-thymeleaf/src/main/java/com/today/house/controller/AnonsController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/qasanov/pay-day-bank/blob/28efcd9cacb75c9b07a55634f07b6414dc34a473/pbd-ms-account/src/main/java/com/paydaybank/ms/account/web/AccountController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/skullacy/mydearnest-web/blob/cab6fbffba02c5b94cbc20bbe287db6149723652/mydearnest-web/src/main/java/com/osquare/mydearnest/api/web/APILoginController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/benbaxter/beer-buddy/blob/0087541672a2d891c92aaed5617f5cb79d7f20df/beer-buddy-web/src/main/java/com/beerbuddy/web/controller/rest/UserController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/visualkhh/lib-spring/blob/89e7d4b9a6318138d24f81e4e0fc2646f2d6a3d5/boot/security-jpa-thymeleaf/src/main/java/com/today/house/controller/AnonsController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/kdevkr/spring-demo-security/blob/68ec92d0fc55cce42c8756dab4fb3258b8ef19d1/web-security/src/main/java/kr/kdev/demo/api/LoginApi.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````

## License: unknown

https://github.com/qasanov/pay-day-bank/blob/28efcd9cacb75c9b07a55634f07b6414dc34a473/pbd-ms-account/src/main/java/com/paydaybank/ms/account/web/AccountController.java

`````
I see the issue. You have conflicting imports and wrong type casting in your FloodReportController.

**Problems:**

1. ✅ **Wrong import** — `import javax.naming.AuthenticationException;` is a JNDI exception, not Spring Security
2. ✅ **Wrong cast** — Trying to cast `AuthenticationException` to `Resource.AuthenticationType` (doesn't exist)
3. ✅ **Dead import** — `jakarta.annotation.Resource.AuthenticationType` is not valid

**Fixed FloodReportController.java:**

````java
// filepath: d:\AtomicFive\flood-alert-backend\src\main\java\Atomic5\demo\controller\FloodReportController.java

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

`````
