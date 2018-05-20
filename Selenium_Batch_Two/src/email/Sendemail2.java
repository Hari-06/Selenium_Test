package email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Sendemail2 {

	public static void main(String[] args) throws EmailException {
		
		System.out.println("---------------Starting the test--------------");

        Email email = new SimpleEmail();

        email.setHostName("smtp.office365.com");

        email.setSmtpPort(587);

        email.setAuthentication("hariharan.m45@wipro.com", "Jan#2018");
email.setStartTLSEnabled(true);
   //     email.setSSLOnConnect(true);

        email.setFrom("hariharan@wipro.com");

        email.setSubject("Selenium test report");

        email.setMsg("This is a test mail ... :-)");

        email.addTo("hariharan.shankar@wipro.com");

        email.send();

        System.out.println("---------------email sent--------------");
	}

}
