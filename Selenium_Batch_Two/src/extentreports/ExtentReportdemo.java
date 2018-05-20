package extentreports;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportdemo {
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeTest
	public void starttest(){
		htmlreporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/report.html");
		reports.attachReporter(htmlreporter);
	}
	@Test
public void passtest(){
	System.out.println("this is my pass test");
	Assert.assertTrue(true);
}
	@Test
public void failtest(){
	System.out.println("this is my fail test");
	Assert.assertFalse(false);
}
	@Test
public void skiptest(){
	System.out.println("this is my skip test");
	throw new SkipException("Skip Test forcefully");
}
	@AfterMethod
	public void tearDown() {
		reports.flush();
	}
}
