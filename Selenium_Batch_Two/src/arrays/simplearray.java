package arrays;

public class simplearray {

	public static void main(String[] args) {
		
		
	// datatype[]  arrayname =  new datatype[arraysize];
		 int [] i = new int[5];
		
		
/*		i[0]=1;
		i[1]=2;
		i[2]=3;
		i[3]=4;
		i[4]=5;
		*/
		for(int j=0;j<i.length;j++) {
			i[j]=j+1;
			System.out.println(i[j]);
			
		}
	}

}
