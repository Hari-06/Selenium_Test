package abstractclass;

public class Abstractdemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Balamobilephone obj1=new Kiranmobilephone();
		obj1.calling();
		obj1.dance();
		obj1.sing();
	}
}
abstract class Balamobilephone{
	public void calling(){
		System.out.println("calling...");
	}
	public abstract void dance();
	public abstract void sing();
}
abstract class harimobilephone extends Balamobilephone {
	public void dance() {
		System.out.println("dance..");
	}
}
class Kiranmobilephone extends harimobilephone{
	public void sing() {
		System.out.println("sing...");
	}
}