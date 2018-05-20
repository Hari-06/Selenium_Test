package extentreports;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import wrappers.GenericWrappers;



public class Report extends GenericWrappers{
	
	public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    
	
    @BeforeSuite
    public void report_Start() throws UnknownHostException
    {
        htmlReporter = new ExtentHtmlReporter("./MyReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Host Name", "Wipro");
        extent.setSystemInfo("Environment", "UAT");
        extent.setSystemInfo("User Name", "Hariharan");
        htmlReporter.config().setDocumentTitle("Report");
        htmlReporter.config().setReportName("Word Press Report");  
    }
    
    @AfterMethod
    public void getResult(ITestResult result) throws IOException
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(test.addScreenCaptureFromPath(Screenshot)+" Test case FAILED due to below issues :", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            
        	test.pass(MarkupHelper.createLabel(result.getName()+test.addScreenCaptureFromPath(Screenshot)+" Test Case PASSED", ExtentColor.GREEN));
            test.log(Status.INFO,"Snapshot below "+ test.addScreenCaptureFromPath(Screenshot));
            
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(" Test Case SKIPPED due to below issues :",ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
    
    
        
    public ExtentTest startTestCase(String TestcaseNo, String testDescription, String Category, String Authors)
    {
    	test = extent.createTest(TestcaseNo+Thread.currentThread().getStackTrace()[2].getMethodName(),testDescription);
        test.assignCategory(Category);
        test.assignAuthor(Authors);
        
        return test;
	}
    
    public ExtentTest log(Status Logtype,String LogMessage) 
    {
    	test.log(Logtype,LogMessage);
    	return test;
    			
        
    }
    
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
        
    }
    
    
	

}
