package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Basicdemo {
	@Test
	public void login1() {
		System.out.println("Login successful");
		Assert.assertEquals(12, 13);
	}
	@Test
	public void compose1() {
		System.out.println("Mail composed");
	}

	@Test
	public void Sendmail1() {
		System.out.println("mail Sent");
	}

	
}
