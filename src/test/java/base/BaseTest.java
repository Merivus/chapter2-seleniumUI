package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {
  public static ExtentReports extentReports;
  public static ExtentSparkReporter sparkReporter;
  public static ExtentTest test;

  public static void setupReport() {
    sparkReporter = new ExtentSparkReporter("target/ExtentReport.html");
    sparkReporter.config().setDocumentTitle("Automation Report");
    sparkReporter.config().setReportName("Test Execution Report");

    extentReports = new ExtentReports();
    extentReports.attachReporter(sparkReporter);

    extentReports.setSystemInfo("Tester", "Sinem Merve Özdemir");
    extentReports.setSystemInfo("Browser", "Chrome");
    extentReports.setSystemInfo("Environment", "QA");
  }

  public static void flushReport() {
    if (extentReports != null) {
      extentReports.flush();
      System.out.println("Report flushed successfully.");
    }
  }
//
//  public static void tearDownReport() {
//    extentReports.flush();
//  }
}
