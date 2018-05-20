package interfacedemo;

public class Interfacesecurity implements InterfaceA{

	public void show() {
		System.out.println("show");
		
	}
	public void securemethod() {
		System.out.println("secure method");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Interfacesecurity obj1=new Interfacesecurity();
		obj1.show();
		obj1.securemethod();
	}
}
