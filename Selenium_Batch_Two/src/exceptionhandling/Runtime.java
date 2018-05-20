package exceptionhandling;

import java.util.InputMismatchException;

public class Runtime {

	public static void main(String[] args) {

		System.out.println("start");
		try {
			int c=10/0;
			System.out.println("the result is"+c);
		}
		catch(ArithmeticException e) {
			System.out.println("something went wrong"+e.getMessage());
			System.out.println("Seems you have entered zero please provide value>0");

		}
		catch(InputMismatchException e)
		{
			System.out.println("pls enter only integer values "+e.getMessage());
		}
catch(Exception e) {
		System.out.println("OOps something went wrong");
}
finally {
	System.out.println("end");
}

	}

}
