package email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Sendemail {

	public static void main(String[] args) throws EmailException {

		System.out.println("---------------Starting the test--------------");

		Email email = new SimpleEmail();

		email.setHostName("smtp.gmail.com");

		email.setSmtpPort(465);

		email.setAuthentication("Your mail ID", "Password");

		email.setSSLOnConnect(true);

		email.setFrom("selenium.batchone@gmail.com");

		email.setSubject("Selenium test report_1/17/2018");

		email.setMsg("Selenium batch two  ... :-)");

		email.addTo("hari.201089@gmail.com");

		email.send();

		System.out.println("---------------email sent--------------");
	}

}
