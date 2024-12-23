package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.Driver;
import base.BaseTest; // Import BaseTest class

public class SearchSteps extends BaseTest {

  WebDriver driver = Driver.getDriver();
  HomePage homePage = new HomePage(driver);

  @Given("I navigate to Search at {string}")
  public void iNavigateToSearch(String url) {
    driver.get(url);
    System.out.println("Navigated to Search URL: " + url);
  }

  @And("I accept cookies if present")
  public void iAcceptCookiesIfPresent() {
    homePage.acceptCookies();
  }

  @When("I click the search button")
  public void iClickTheSearchButton() {
    homePage.clickSearchButton();
  }

  @And("I enter the search term {string}")
  public void iEnterTheSearchTerm(String searchTerm) {
    homePage.enterSearchTerm(searchTerm);
  }

  @And("I submit the search")
  public void iSubmitTheSearch() {
    homePage.submitSearch();
  }

  @Then("I should see search results containing {string}")
  public void iShouldSeeSearchResultsContaining(String keyword) {
    boolean isResultValid = homePage.validateSearchResults(keyword);
    Assert.assertTrue("Search results do not contain the keyword: " + keyword, isResultValid);
  }
}