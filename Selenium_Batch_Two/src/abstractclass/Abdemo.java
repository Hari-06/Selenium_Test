package abstractclass;

public class Abdemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mobphone1 obj1= new Mobphone3();
		obj1.call();
		obj1.dance();
		obj1.sing();
	}

}

abstract class Mobphone1{
	public void call() {
		System.out.println("calling..");
	
	}
	public abstract void dance(); 
	public abstract void sing(); 
}
abstract class Mobphone2 extends Mobphone1{
	public void dance() {
		System.out.println("dance");
	}
}
class Mobphone3 extends Mobphone2{
	public void sing() {
		System.out.println("sing..");
	}
}