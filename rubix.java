import java.util.Scanner;

public class rubix{
	public static int algCheck(Cube ruben, String algorithim){
		int count = 0;
		do{
			ruben.moveSet(algorithim);
			count++;
		}while(!ruben.solvedCube()&&count<400);
		if (count==400)
			return -1;
		return count;
	}
	public static void main(String[] args){
		Scanner Scan = new Scanner(System.in);
		Cube ruben = new Cube();
		ruben.printFaces();
		ruben.printFN();
		String input = "";
		String algorithim = "R,B,L,F";
		int count = 0;
		do{
			ruben.moveSet(algorithim);
			count++;
		}while(!ruben.solvedCube());
		System.out.println("Count: "+count);
		ruben.printFaces();
		while(!input.equals("exit")){
			input = Scan.nextLine();
			if(input.equals("check"))
				System.out.println("Alg Time"+algCheck(ruben,algorithim));
			System.out.println("MoveValidity:"+ruben.moveSet(input));
			ruben.printFaces();
		}
	}
}