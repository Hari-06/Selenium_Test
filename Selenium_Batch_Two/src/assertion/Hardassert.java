package assertion;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Hardassert {
@Test
	public void test1() {
		Assert.assertEquals(12, 13);
	}
@Test
	public void test2() {
		Assert.assertEquals(12, 13,"Values did not match");
	}
@Test
	public void test3() {
		Assert.assertEquals("Hello","Hello","Values did not match");
	}
	
}
