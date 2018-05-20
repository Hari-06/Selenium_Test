package constructor;

public class Parameterconstructor {
	String name;
	int age;
	String address;
	
	Parameterconstructor(String n,int a,String add){
		name=n;
		age=a;
		address=add;
		System.out.println(name);
		System.out.println(age);
		System.out.println(address);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parameterconstructor pc=new Parameterconstructor("hari",28,"chennai");
	}

}
