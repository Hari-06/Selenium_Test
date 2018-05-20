package testng_demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNgattributes {
@Test
	public void test1() {
	String str="Hariharan Shankar";
		Assert.assertTrue(str.contains("asd"),"String does not present");
	//Assert.assertTrue(true);
	}
@Test
public void test2() {
	Assert.assertTrue(false);
}

	
}
