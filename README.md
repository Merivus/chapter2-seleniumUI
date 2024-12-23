# Chapter2 Selenium UI Tests 🚀

This project contains Selenium-based UI automation tests designed for validating the functionality of specific web applications. The tests cover scenarios such as verifying search functionality, navigating to documents, downloading files, and more.

## Project Setup 🛠️

Follow these steps to set up the project locally and start running tests.

### Prerequisites

Ensure you have the following installed on your machine:

- **Java JDK 17** or higher
- **Maven** for dependency management
- **Google Chrome** (latest version)
- **Git** for version control
- Optional: **IntelliJ IDEA** for development

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Merivus/chapter2-seleniumUI.git
   cd chapter2-seleniumUI
   ```

2. Install dependencies:

   ```bash
   mvn clean install
   ```

3. Ensure the required WebDriver is set up automatically using WebDriverManager.

## Running Tests 🧪

### Locally

Run the tests using the following Maven command:

```bash
mvn test
```

### CI Pipeline

The tests are integrated into a CI pipeline. The pipeline includes steps for building, running tests, and validating results.

---

## Key Features ✨

### TechDocs Automation
- Navigate to the TechDocs page.
- Select specific documents from the dropdown.
- Open, view, and verify document download functionality.

### Search Functionality
- Navigate to the search page.
- Accept cookies dynamically.
- Enter search terms and validate results.

---

## Troubleshooting 🛠️

### Common Issues

#### `java.lang.AssertionError: Document was not downloaded!`

- Ensure the document download location is correct and accessible.
- Verify the file type and name patterns match your test logic.

#### `error: RPC failed; HTTP 400`
- Update your Git remote URL with a valid personal access token (PAT).

#### WebDriver/CDP Version Issues
- Add the correct Selenium DevTools dependency based on your browser version, e.g.:
  ```xml
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-devtools-v131</artifactId>
    <version>4.8.3</version>
  </dependency>
  ```

---

## Technologies Used 🖥️

- **Selenium 4.8.3**
- **Cucumber for BDD**
- **JUnit for Assertions**
- **WebDriverManager**
- **Java 17**
- **Maven**

---

## Contribution 👥

Feel free to contribute to this repository. To contribute:

1. Fork the repo.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add a cool feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

---

## 📞 Contact
Feel free to reach out to me:
- **Name**: Sinem Merve Ozdemir
- **Email**: sinemmerveyilmaz@gmail.com
- **GitHub**: [Merivus](https://github.com/merivus)

---

💡 Happy Testing! 🚀
