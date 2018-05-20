package wrappers;

import wrappers.GenericWrappers;

public class Irctc extends GenericWrappers {


	public void Signup() throws InterruptedException 
	{
		invokeApp("chrome", "https://www.irctc.co.in/eticketing/loginHome.jsf");
		clickByLink("Sign up");
		enterById("userRegistrationForm:userName","hari89");
		enterById("userRegistrationForm:password","Aug@2017");
		enterById("userRegistrationForm:confpasword","Aug@2017");
		enterById("userRegistrationForm:firstName","Hariharan");
		enterById("userRegistrationForm:middleName","Rani");
		enterById("userRegistrationForm:lastName","Shankar");
		closeAllBrowsers();
	}


	public static void main(String[] args) throws InterruptedException 
	{
		Irctc obj = new Irctc();
		obj.Signup();
	}

}
