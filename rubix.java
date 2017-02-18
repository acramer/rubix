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
		String input = "";
		String algorithim = "R,B,L,F";
		String tempAlg = "";
		while(!input.equals("exit")){
			input = Scan.nextLine();
			if(input.equals("algorithim")){
				System.out.println("*NOTE* if you input a invalid algorithim, the last valid algorithim will be run\nInput Algorithim: ");
				tempAlg = Scan.nextLine();
				if(ruben.isValid(tempAlg)){
					algorithim = tempAlg;
				}
				System.out.println("Alg Time "+algCheck(ruben,algorithim));
			}
			else
				System.out.println("MoveValidity:"+ruben.moveSet(input));
			ruben.printFaces();
		}
	}
}