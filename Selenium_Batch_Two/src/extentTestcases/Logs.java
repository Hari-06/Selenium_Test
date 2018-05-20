package extentTestcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import extentreports.Report;

public class Logs extends Report {
@Test
public void Logs_Generate()
{
	startTestCase("TC_04", "Generating Logs", "Logs", "Unknown");
	
	log(Status.ERROR, "This is an example of Error Log");
	log(Status.FAIL, "This is an example of Fail Log");
	log(Status.FATAL, "This is an example of Fatal Log");
	log(Status.INFO, "This is an example of Info Log");
	log(Status.PASS, "This is an example of Pass Log");
	log(Status.SKIP, "This is an example of Skip Log");
	log(Status.WARNING, "This is an example of Warning Log");
		
	
}
}
