package extentreports;

import org.junit.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Extentreportv3 {
	ExtentReports reports;
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;

	@BeforeTest
	public void starttest() {
		// specify where your report file will be stored
	//	htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//report.html");
		htmlReporter = new ExtentHtmlReporter(".//test-output//report.html");
		// attach the html file to extent reports
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		//load extent-config file
		htmlReporter.loadXMLConfig(".//test-output//extent-config.xml");
		// configure environment info
		reports.setSystemInfo("Hostname", "localhost");
		reports.setSystemInfo("Browser", "chrome");
		reports.setSystemInfo("Environment", "UAT");
		reports.setSystemInfo("Region", "UA1");
	}
	@Test
	public void passtest() {
		// map test case to Extent test
		test = reports.createTest("passtest","This will demonstrate the pass");
		Assert.assertTrue(true);
	}
	@Test
	public void failtest() {
		test = reports.createTest("failtest","This will demo the fail");
		Assert.assertTrue(false);
	}
	@Test
	public void skiptest() {
		test = reports.createTest("skiptest","This will demo the skip");
		throw new SkipException("Skip Test forcefully");
	}
	@AfterMethod
	public void setTestresults(ITestResult result){
		if(result.getStatus()== ITestResult.FAILURE){
			test.log(Status.FAIL,MarkupHelper.createLabel(result.getName()+ "Test case failed due to below issues",ExtentColor.RED));
			test.fail(result.getThrowable());
		}
		else if(result.getStatus()== ITestResult.SUCCESS){
			test.log(Status.PASS,MarkupHelper.createLabel(result.getName()+ "Test case Passed",ExtentColor.GREEN));

		}
		else{
			test.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+ "Test case Skipped",ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}
	}
	@AfterTest
	public void endtest() {
		reports.flush();
	}
}
