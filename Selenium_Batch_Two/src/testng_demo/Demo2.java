package testng_demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo2 {
@Test
	public void login2() {
		System.out.println("Login Successfull2");
		Assert.assertEquals(13, 14);
	}

@Test
public void compose2() {
	System.out.println("Mail Composed2");
}

@Test
public void sendEmail2() {
	System.out.println("Email Sent2");
}

}
