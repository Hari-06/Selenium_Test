package assertion;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Hardassert2 {
@Test()
	public void test1() {
	String str="Hariharan Shankar";
		Assert.assertTrue(str.contains("Shankar"),"String does not present");
		System.out.println(str);
	//Assert.assertTrue(true);
	}


	
}
