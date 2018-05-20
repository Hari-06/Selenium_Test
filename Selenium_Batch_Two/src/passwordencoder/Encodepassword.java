package passwordencoder;

import org.apache.commons.net.util.Base64;

public class Encodepassword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String password="ashwin201089";
		
		//Encode password
		byte[] encodedpassword=Base64.encodeBase64(password.getBytes());
		
		String encoded = new String(encodedpassword);
		System.out.println("encoded password is "+encoded);
		//System.out.println("encoded password is "+encodedpassword);
		
		//Decode password
		byte[] decodedpassword=Base64.decodeBase64(encoded.getBytes());
		System.out.println("Decoded password is "+new String(decodedpassword));

	}

}
