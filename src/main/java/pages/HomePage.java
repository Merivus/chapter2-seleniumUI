package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import utils.Driver;

public class HomePage {

  private WebDriver driver;
  private WebDriverWait wait;

  // Locators
  private By acceptCookiesButton = By.id("cookienotice-button-accept"); // Cookie button
  private By searchButton = By.id("LELY_SEARCH_TOP"); // Search button
  private By searchInput = By.id("global-search"); // Search input box
  private By submitSearch = By.xpath("//button[@type='submit']"); // Search submit button
  private By searchResults = By.xpath("//ul[contains(@class, 'search-results')]"); // Search results


  // Constructor
  public HomePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
  }

  // Accept cookies method
  public void acceptCookies() {
    try {
      WebElement cookiesButton = wait.until(ExpectedConditions.presenceOfElementLocated(acceptCookiesButton));
      if (cookiesButton.isDisplayed()) {
        cookiesButton.click();
        System.out.println("Clicked on the accept cookies button.");
      }
    } catch (TimeoutException e) {
      System.out.println("Accept cookies button not present.");
    }
  }

  // Click search button method
  public void clickSearchButton() {
    try {
      WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LELY_SEARCH_TOP")));
      searchButton.click();
      System.out.println("Search button clicked successfully.");
    } catch (ElementClickInterceptedException e) {
      System.out.println("Search button not clickable. Trying JavaScript...");
      try {
        WebElement searchButton = driver.findElement(By.id("LELY_SEARCH_TOP"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));", searchButton);
        System.out.println("JavaScript click executed successfully.");
      } catch (JavascriptException jsException) {
        System.out.println("JavaScript click failed: " + jsException.getMessage());
      }
    }
  }

  // Enter search term method
  public void enterSearchTerm(String term) {
    try {
      WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("global-search")));
      searchBox.sendKeys(term);
      System.out.println("Search term entered: " + term);
    } catch (TimeoutException e) {
      System.out.println("Search box not found or not visible within timeout. Error: " + e.getMessage());
    }
  }

  // Submit search method
  public void submitSearch() {
    try {
      WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("global-search")));
      searchBox.sendKeys(Keys.ENTER);
      System.out.println("Search submitted.");
    } catch (Exception e) {
      System.out.println("Unable to submit the search. Error: " + e.getMessage());
    }
  }

  // Validate search results
  public boolean validateSearchResults(String keyword) {
    try {
      WebElement results = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
      String resultsText = results.getText();
      System.out.println("Search results validated.");
      return resultsText.contains(keyword);
    } catch (TimeoutException e) {
      System.out.println("Search results not found or not visible within timeout. Error: " + e.getMessage());
      return false;
    }
  }
}
