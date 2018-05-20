package java_Functions;

public class Stringfunctions2 {
	public static void main(String[] args){
		/**
		 * String class to create and manipulate strings.
		 * 
		 * Methods
		 * 
		 * 0) toCharArray() //
		 * 1) length // find the number of characters		  
		 * 2) charAt(index) // The character at the index		  		  
		 * 3) indexOf(chr) , lastIndexOf(chr) // the index of the first or last match
		 * 4) concat //Concatenate two strings only  2+"Std"
		 * 6) subString(beginIndex, endIndex) //Extracts a particular portion of String
		 * 7) trim // Removes the white space on begining and End
		 * 8) toLowerCase, toUpperCase // to convert case		  
		 * 9) split('char') -- split(regex, limit) //		 	  
		 * 10)startsWith(s), endsWith(s), contains(s) // match 
		 * 11)replace(oldChar, newChar), replaceAll(regex, replacement) // 
		* 12) String, StringBuffer(ThreadSafe), StringBuilder(Not ThreadSafe) 
		 */

	
		//find length
		String text = " Cognizant "; //As literals
		System.out.println(text.length());
		
		//convert string to char
		char[] textOfChar = text.toCharArray();
		System.out.println(textOfChar);

		//charAt(index);
		char ch=text.charAt(2); 
		System.out.println(ch);
		
		//indexof(chr);
		int index=text.indexOf('g');
		System.out.println(index);
		
		//LastIndexof(chr);
		int lastindex=text.lastIndexOf('n');
		System.out.println(lastindex);

		// concat
		System.out.println("The concatenation can be :"+text.concat("Chennai"));
		
		//Substring(beginIndex,endIndex)
		String first=text.substring(0, 3);
		System.out.println(first);
		String mid=text.substring(3, 5);
		System.out.println(mid);
		String tilllast=text.substring(5);
		System.out.println(tilllast);
		
		//trim - removes white space
		System.out.println("Without white spaces on both end :"+text.trim());

		//To lowercase and to uppercase
		String a="INDIA";
		String b="usa";
		System.out.println(a.toLowerCase());
		System.out.println(b.toUpperCase());
		
		//Split
		String str="Amazon India Dev Center";
		String[] words=str.split(" ");
		System.out.println(words.length);
		
		//replace and replace All
		String rep=a.replace('A','I');
		System.out.println(rep);
		
		//replaceAll
		String company="IBM(20141)";
		// \\D replaced everything which is not number with empty values
		String rep1=company.replaceAll("\\D", " ");
		System.out.println(rep1);
		//replace numbers-alone
		String rep2=company.replaceAll("\\d", " ");
		System.out.println(rep2);
				
					
		
	}	
}
