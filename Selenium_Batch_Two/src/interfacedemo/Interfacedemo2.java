package interfacedemo;

public class Interfacedemo2 implements InterfaceA,InterfaceB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interfacedemo2 obj1=new Interfacedemo2();
		obj1.display();
		obj1.show();
	}

	public void display() {
		// TODO Auto-generated method stub
		System.out.println("display..");
	}

	public void show() {
		// TODO Auto-generated method stub
		System.out.println("show..");
	}

}
