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
		System.out.println("This is a rubix cube simulator
							\nThe characters \"U\",\"F\",\"R\",\"B\",\"L\",\"D\", are mapped to clockwise rotation of the Top, Front, 
							Right, Back, Left, and Bottem Faces respectively.
							\nMove sets can be input with moves separated either by commas (\",\") or spaces (\" \").
							\n\nTo test an algorithim type \"algorithim\", and to exit the simulator type \"exit\".");
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