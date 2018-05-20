package interfacedemo;

public class Interfacedemo implements InterfaceA,InterfaceB {

	public void display() {
System.out.println("display");

	}

	public void show() {
System.out.println("show");
	}
	
	public void capture() {
		System.out.println("capture");
	}
public static void main(String[] args) {
	Interfacedemo obj1=new Interfacedemo();
	obj1.display();
	obj1.show();
	obj1.capture();
}
}
