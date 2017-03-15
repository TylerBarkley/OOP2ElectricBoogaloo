package model.Map;

public class Temp {
	public static void main(String[] a){
		for(int i=0;i<21;i++){
			for(int j=0; j<75; j++){
				System.out.print((int)(Math.random()*500)+500 + " ");
			}
			
			System.out.println();
		}
	}
}
