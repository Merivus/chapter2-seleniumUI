package hooks;

import base.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

  @Before
  public void beforeScenario(Scenario scenario) {
    setupReport(); // Raporu başlat
    test = extentReports.createTest(scenario.getName()); // Senaryoyu rapora ekle
    test.info("Scenario started: " + scenario.getName());
  }

  @After
  public void afterScenario(Scenario scenario) {
    if (scenario.isFailed()) {
      test.fail("Scenario failed: " + scenario.getName());
    } else {
      test.pass("Scenario passed: " + scenario.getName());
    }
    flushReport(); //
  }
}
