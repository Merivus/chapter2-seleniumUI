package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.io.File;

public class TechDocsPage {

  private WebDriver driver;
  private WebDriverWait wait;

  // Locators
  private By dropdown = By.cssSelector("span.select2-selection--single"); // Dropdown locator
  private By searchBox = By.cssSelector("input.select2-search__field"); // Search box locator
  private By catalogs = By.id("items-list");
  private By viewDocumentButton = By.xpath("(//a[@target='_blank' and contains(@href, 'mode=view')])[1]");
  private By downloadDocumentButton = By.xpath("(//a[contains(@class, 'button-secondary icon-pdf') and contains(@href, 'mode=download')])[1]");

  // Constructor
  public TechDocsPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  // Method to select an option from the dropdown
  public void selectFromDropdown(String option) {
    // Open the dropdown
    WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(dropdown));
    dropdownElement.click();

    // Type the search term
    WebElement searchBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
    searchBoxElement.sendKeys(option);

    // Click the desired option
    WebElement dropdownOption = wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//li[contains(@class, 'select2-results__option') and text()='Luna (all documents)']")));
    dropdownOption.click();

    System.out.println("Option 'Luna (all documents)' selected successfully.");
  }

  // Method to check if catalogs are visible
  public boolean areCatalogsVisible() {
    return driver.findElements(catalogs).size() > 0;
  }

  // Method to view a document
  public void viewDocument() {
    driver.findElement(viewDocumentButton).click();
  }

  // Method to download a document
  public void downloadDocument() {
    driver.findElement(downloadDocumentButton).click();
  }

  // Method to verify if a document is downloaded
  public boolean isDocumentDownloaded(String fileName, int timeoutInSeconds) {
    String downloadPath = System.getProperty("user.dir") + "/downloads";
    File folder = new File(downloadPath);
    File[] files = folder.listFiles();

    if (files == null || files.length == 0) {
      System.out.println("No files found in the downloads folder.");
      return false;
    }

    for (File file : files) {
      System.out.println("Checking file: " + file.getName());
      if (file.getName().endsWith(".pdf")) { // İndirilen dosyanın PDF olduğunu kontrol et
        System.out.println("Found downloaded file: " + file.getName());
        return true;
      }
    }

    System.out.println("No PDF file found in the downloads folder.");
    return false;
  }
}
