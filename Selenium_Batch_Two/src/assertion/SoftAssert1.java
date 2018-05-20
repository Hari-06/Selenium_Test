package assertion;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssert1 {
	@Test
	public void soft() {
		SoftAssert assertion=new SoftAssert();
		System.out.println("Test started");
		assertion.assertEquals(12, 13);
		System.out.println("Test completed");
		assertion.assertAll();
	}

}
