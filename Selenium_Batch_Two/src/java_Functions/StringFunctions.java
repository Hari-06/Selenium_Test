package java_Functions;

public class StringFunctions {

	public static void main(String[] args) {
		String myText = "Hello World";
		
		//Find String Length
		int  len = myText.length();
		System.out.println("Length of  "+myText+" is "+len);
		
		//Concatinating Strings
		String text1 ="Hari";
		String text2 = "Haran";
		System.out.println(text1+text2);
		System.out.println("My Name is ".concat("Hari"));

		//Uppercase and LowerCase
		String lower ="hai";
		String upper ="HAI";
		System.out.println(lower.toUpperCase());
		System.out.println(upper.toLowerCase());
		
		//Trim
		String txt ="      Wlcome    to ";
	}

}
