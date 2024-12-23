package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.TechDocsPage;
import utils.Driver;
import utils.Driver;

public class TechDocsSteps {

  private WebDriver driver = Driver.getDriver();
  private TechDocsPage techDocsPage = new TechDocsPage(driver);

  @Given("I navigate to TechDocs at {string}")
  public void iNavigateToTechDocs(String url) {
    driver.get(url);
    System.out.println("Navigated to TechDocs URL: " + url);
  }

  @When("I select {string} from the dropdown")
  public void iSelectFromTheDropdown(String option) {
    techDocsPage.selectFromDropdown(option);
  }

  @Then("I should see the catalogs")
  public void iShouldSeeTheCatalogs() {
    boolean catalogsVisible = techDocsPage.areCatalogsVisible();
    Assert.assertTrue("Catalogs are not visible!", catalogsVisible);
  }

  @When("I view a document")
  public void iViewADocument() {
    techDocsPage.viewDocument();
    System.out.println("Document viewed successfully.");
  }

  @Then("The document should open in a new tab")
  public void theDocumentShouldOpenInANewTab() {
    String originalTab = driver.getWindowHandle(); // Get the original tab's handle
    boolean isNewTabOpened = false;

    // Wait for a new tab to open
    for (String windowHandle : driver.getWindowHandles()) {
      if (!windowHandle.equals(originalTab)) {
        driver.switchTo().window(windowHandle); // Switch to the new tab
        isNewTabOpened = true;
        break;
      }
    }

    // Verify if the new tab contains the expected content
    Assert.assertTrue("New tab did not open or URL is incorrect!",
        isNewTabOpened && driver.getCurrentUrl().contains("mode=view"));

    System.out.println("Document successfully opened in a new tab.");
  }

  @When("I return to the previous tab")
  public void iReturnToThePreviousTab() {
    String originalHandle = driver.getWindowHandles().iterator().next();
    driver.switchTo().window(originalHandle);
    System.out.println("Switched back to the original tab.");
  }

  @When("I download the document")
  public void iDownloadTheDocument() {
    techDocsPage.downloadDocument();
    System.out.println("Document download initiated.");
  }

  @Then("I should verify the document is downloaded")
  public void iShouldVerifyTheDocumentIsDownloaded() {
    // Belirli bir dosya uzantısı ve maksimum bekleme süresi belirliyoruz
    boolean isDownloaded = techDocsPage.isDocumentDownloaded(".pdf", 10);
    Assert.assertTrue("Document was not downloaded!", isDownloaded);
  }
}
