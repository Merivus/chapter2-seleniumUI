package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

  private static WebDriver driver;

  // Private constructor to prevent instantiation
  private Driver() {}

  // Singleton pattern to ensure only one instance of WebDriver
  public static WebDriver getDriver() {
    if (driver == null) {
      try {
        System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir") + "/chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");

        WebDriverManager.chromedriver().setup();

        // Set preferences for file download
        String downloadPath = System.getProperty("user.dir") + "/downloads";
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs); // Add preferences

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");

        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-extensions");
        options.addArguments("--enable-logging");
        options.addArguments("--log-level=0");

        driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to initialize the WebDriver: " + e.getMessage());
      }
    }
    return driver;
  }

  // Quit and clean up the driver
  public static void closeDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
