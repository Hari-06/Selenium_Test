package testng_demo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo1 {
@Test
	public void login() {
		System.out.println("Login Successfull");
		Assert.assertEquals(13, 14);
	}

@Test
public void compose() {
	System.out.println("Mail Composed");
}

@Test
public void sendEmail() {
	System.out.println("Email Sent");
}

}
