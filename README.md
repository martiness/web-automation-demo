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
src/
└── test/
    ├── java/
    │   └── com/demo/ui/
    │       ├── core/               # BaseTest, DriverFactory, ResolutionManager
    │       ├── pages/              # Page Object classes (LoginPage, CartPage, etc.)
    │       ├── tests/              # High-level scenario tests (e.g., CheckoutFlowTest)
    │       ├── exploratory/        # Additional or low-level exploratory tests
    │       └── utils/              # ConfigReader, BrowserResolution, etc.
    └── resources/
        └── config/                 # Environment-specific config files (dev.properties, etc.)

README.md                            # Project overview and instructions
pom.xml                              # Maven build configuration

```

---

---

## 🧱 Architecture & Principles

This project follows the **Page Object Model (POM)** and applies **SOLID design principles**, especially:

- **SRP (Single Responsibility Principle):**
    - `BaseTest` handles test lifecycle only
    - `DriverFactory` handles WebDriver instantiation
    - `ResolutionManager` sets window size
    - `ConfigReader` handles all environment-specific configuration

This separation allows easy scaling, maintenance, and extension of the framework.

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

## 📂 Logging and Debugging

This project uses SLF4J with Logback for structured logging.

    Logs are printed to the console

Example output:
```
14:05:22 INFO  [LoginPage] - Attempting login with username: standard_user
14:05:23 INFO  [InventoryPage] - Added first and last item to cart
```
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

- Add test data providers
- Add headless browser toggle
- Dockerize test execution

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 👤 Author
Martin Kenov

_This project is built for learning and demonstration purposes._
