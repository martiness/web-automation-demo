# 🧪 Web UI Automation Demo

This project demonstrates a functional UI test automation framework using **Java**, **Selenium**, **JUnit 5**, and **Allure Reports**. It fulfills the following objectives:

---

## ✅ Functional Goals

- 🔹 Automates a real-world web application: [SauceDemo](https://www.saucedemo.com/)
- 🔹 Uses `JUnit 5` for test orchestration and tagging
- 🔹 Supports execution filtering via `@Tag`
- 🔹 Executes tests against multiple environments (dev, test, staging)
- 🔹 Supports execution in Chrome and Firefox browsers
- 🔹 Allows browser resolution customization
- 🔹 Generates HTML reports via **Allure**

---

## ⚙️ Tech Stack

| Layer        | Technology           |
|--------------|----------------------|
| Language     | Java 11+             |
| Test Runner  | JUnit 5              |
| UI Driver    | Selenium WebDriver 4.25 |
| Build Tool   | Maven                |
| Reporting    | Allure               |
| Browsers     | Chrome, Firefox      |

---

## 🧭 Project Structure

```
src
├── main
│   └── java
│       └── com.demo.utils         → ConfigReader, BrowserResolution
├── test
│   └── java
│       ├── com.demo.ui           → Main scenario tests
│       └── com.demo.ui.exploratory → Component/utility tests
└── resources
    └── config
        ├── dev.properties
        ├── test.properties
        └── staging.properties
```

---

## 🧪 Test Scenarios

### ✅ Scenario 1 – Full Checkout Flow
- Log in
- Add first and last product to cart
- Remove one, add another
- Verify cart content
- Complete checkout
- Assert cart is empty
- Logout

### ✅ Scenario 2 – Sorting
- Log in
- Sort products by price (high to low)
- Verify sorted prices

---

## 🚀 Test Execution

### 🔹 Default (dev env, chrome, 1920x1080):
```bash
mvn clean test
```

### 🔹 Run with environment override:
```bash
mvn clean test -Denv=staging
```

### 🔹 Run with browser override:
```bash
mvn clean test -Dbrowser=firefox
```

### 🔹 Filter tests by tag:
```bash
mvn clean test -DincludeTags=checkout
```

> Tags used: `checkout`, `sorting`, `exploratory`

---

## 🌐 Environment Configuration

Environment files are in `src/test/resources/config/`

Example `dev.properties`:
```properties
browser=chrome
browser.resolution=FULL_HD
base.url=https://www.saucedemo.com/
```

### Supported screen resolutions:
- `FULL_HD` → 1920x1080
- `WXGA` → 1366x768
- `HD` → 1280x720
- `SMALL` → 800x600

---

## 📊 Allure Report

1. Run tests:
```bash
mvn clean test
```

2. Serve the report:
```bash
allure serve target/allure-results
```

---

## 🧹 Cleanup

- `target/allure-results` – raw test data
- `target/allure-report` – generated HTML report

---

## 🧠 Possible Enhancements

- Add logging with SLF4J + Logback
- Add test data providers
- Add headless browser toggle
- Dockerize test execution

---

## 👤 Author

_This project is built for learning and demonstration purposes._
