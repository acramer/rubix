//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//To do
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Class Variables
// -None
// Constructor Methods
// -None
// Accessor Methods
// Modifier Methods
// Movement Methods
// Decoder Methods
// - Trim Array Strings so no empty spaces
// - Maybe separate by ' ' as well as ','
// - Turn moves ending with 2 into double moves of the form U,U
// Display Methods
// Equality Methods


public class Cube{

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Class Variables
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private Face topFace;
	private Face frontFace;
	private Face rightFace;
	private Face backFace;
	private Face leftFace;
	private Face bottemFace;
	private String[] presetDCForm = {"U","U'","F","F'","R","R'","B","B'","L","L'","D","D'"};
	private final String[] decodeForm;


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Default Constructor
	public Cube(){
	topFace = new Face(1,frontFace,leftFace,backFace,rightFace);
	frontFace = new Face(2,topFace,rightFace,bottemFace,leftFace);
	rightFace = new Face(3,topFace,backFace,bottemFace,frontFace);
	backFace = new Face(4,topFace,leftFace,bottemFace,rightFace);
	leftFace = new Face(5,topFace,frontFace,bottemFace,backFace);
	bottemFace = new Face(6,frontFace,rightFace,backFace,leftFace);
	topFace.setAllFaces(frontFace,leftFace,backFace,rightFace);
	frontFace.setAllFaces(topFace,rightFace,bottemFace,leftFace);
	rightFace.setAllFaces(topFace,backFace,bottemFace,frontFace);
	backFace.setAllFaces(topFace,leftFace,bottemFace,rightFace);
	leftFace.setAllFaces(topFace,frontFace,bottemFace,backFace);
	bottemFace.setAllFaces(frontFace,rightFace,backFace,leftFace);
	decodeForm = presetDCForm;
	}
	//Import decodeForm Constructor
	public Cube(String[] impDC){
	topFace = new Face(1,frontFace,leftFace,backFace,rightFace);
	frontFace = new Face(2,topFace,rightFace,bottemFace,leftFace);
	rightFace = new Face(3,topFace,backFace,bottemFace,frontFace);
	backFace = new Face(4,topFace,leftFace,bottemFace,rightFace);
	leftFace = new Face(5,topFace,frontFace,bottemFace,backFace);
	bottemFace = new Face(6,frontFace,rightFace,backFace,leftFace);
	topFace.setAllFaces(frontFace,leftFace,backFace,rightFace);
	frontFace.setAllFaces(topFace,rightFace,bottemFace,leftFace);
	rightFace.setAllFaces(topFace,backFace,bottemFace,frontFace);
	backFace.setAllFaces(topFace,leftFace,bottemFace,rightFace);
	leftFace.setAllFaces(topFace,frontFace,bottemFace,backFace);
	bottemFace.setAllFaces(frontFace,rightFace,backFace,leftFace);
	if(impDC.length == 12)
		decodeForm = impDC;
	else
		decodeForm = presetDCForm;
	}
	//Importing Faces Constructor, main use is for cloning a cube state
	public Cube(Face imp0,Face imp1,Face imp2,Face imp3,Face imp4,Face imp5){
	topFace = new Face(1,imp1,imp4,imp3,imp2);
	frontFace = new Face(2,imp0,imp2,imp5,imp4);
	rightFace = new Face(3,imp0,imp3,imp5,imp1);
	backFace = new Face(4,imp0,imp4,imp5,imp2);
	leftFace = new Face(5,imp0,imp1,imp5,imp3);
	bottemFace = new Face(6,imp1,imp2,imp3,imp4);
	decodeForm = presetDCForm;
	topFace.setAllFaces(imp1,imp4,imp3,imp2);
	frontFace.setAllFaces(imp0,imp2,imp5,imp4);
	rightFace.setAllFaces(imp0,imp3,imp5,imp1);
	backFace.setAllFaces(imp0,imp4,imp5,imp2);
	leftFace.setAllFaces(imp0,imp1,imp5,imp3);
	bottemFace.setAllFaces(imp1,imp2,imp3,imp4);
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Accessor Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int[][] getFaceMat(int x){
		switch (x){
			case 0: return topFace.getAllSquares();
			case 1: return frontFace.getAllSquares();
			case 2: return rightFace.getAllSquares();
			case 3: return backFace.getAllSquares();
			case 4: return leftFace.getAllSquares();
			case 5: return bottemFace.getAllSquares();
		}
		return null;
	}
	public Face getFaceClone(int x){
		switch (x){
			case 0: return topFace.clone();
			case 1: return frontFace.clone();
			case 2: return rightFace.clone();
			case 3: return backFace.clone();
			case 4: return leftFace.clone();
			case 5: return bottemFace.clone();
		}
		return null;
	}
	public Cube getCubeClone(){
		return new Cube(topFace.clone(),frontFace.clone(),rightFace.clone(),backFace.clone(),leftFace.clone(),bottemFace.clone());
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Modifier Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Movement Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Calls decoder to get moves in int values, then iterates through the moves calling singleMove for each one.  If the moveDecoder returns a null
	//value, or possible a negative one in the first spot, move set returns a negative one for the purpose of alerting user that a invalid value has
	//been inputed into moveSet.
	public int moveSet(String rawMoveSet){
		int[] decdMoveSet = moveDecoder(rawMoveSet);
		if(decdMoveSet == null)
			return -1;
		for(int i=0;i<decdMoveSet.length;i++)
			singleMove(decdMoveSet[i]);
		return 1;
	}
	//Call setup method based on the decdMove inputed by MoveSet Method, then calls each face's move method
	private void singleMove(int decdMove){
		switch(decdMove){
			case 0: setupUp();
				break;
			case 1: setupUpComp();
				break;
			case 2: setupFront();
				break;
			case 3: setupFrontComp();
				break;
			case 4: setupRight();
				break;
			case 5: setupRightComp();
				break;
			case 6: setupBack();
				break;
			case 7: setupBackComp();
				break;
			case 8: setupLeft();
				break;
			case 9: setupLeftComp();
				break;
			case 10: setupBottom();
				break;
			case 11: setupBottomComp();
				break;
		}
		topFace.move();
		frontFace.move();
		rightFace.move();
		backFace.move();
		leftFace.move();
		bottemFace.move();
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Decoder Method
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Returns int[] of number associated with moves or a null/negative one if the String is invalid
	//rawMoveSet input is a string of the form "U,F,R,R,F',D" where the moves are the strings separated by the commas
	private int[] moveDecoder(String rawMoveSet){
		rawMoveSet = rawMoveSet.toUpperCase().trim();			//<-Takes input and formats it to Uppercase letters removes leading and ending spaces

		// if(rawMoveSet.indexOf(',')==-1)						//<-If a user inputs a rawMoveSet separated by spaces and not commas, the spaces are replaced by
		// 	rawMoveSet.replace('');								//commas and the regular code is run.  Still trying to figure out a way to combat multiple spaces

		while(rawMoveSet.contains(" ")){						//<-Removes any unecessary Spacing, also if two moves are separated by only a space, it replaces
			if(rawMoveSet.contains(", ")){						//these spaces with commas.  Allows for greater range of valid input
				rawMoveSet = rawMoveSet.replaceFirst(", ",",");
				continue;
			}
			rawMoveSet = rawMoveSet.replaceFirst(" ",",");
		}

		rawMoveSet = rawMoveSet.replaceAll("2",",X");			//<-Allows for double moves to be called by replacing two with a character that will be replaced
																//later on by the move before it;

		String[] moveArray = rawMoveSet.split(",");
		int[] decdArray = new int[moveArray.length];
		for(int i=0;i<moveArray.length;i++){
			for(int j=0;j<12;j++){
				if(decodeForm[j].equals(moveArray[i])){
					decdArray[i] = j;
					break;
				}
				else if(j==11){									//<-Checks to see if move is X; if it is, the previous move is placed in this move,
					if(moveArray[i].equals("X")&&i>0&&!moveArray[i-1].equals("X"))
						decdArray[i] = decdArray[i-1];
					else										//if it isn't, or if it is immediatly following another X, the moveset is returned invalid
						return null;							//If a move set is invalid, null is return and no moves are called
				}
			}
		}
		return decdArray;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Setup Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//B *No Method*(this side does not move) - associated number - 0
	//F  - associated number - 1
	//F' - associated number - 2
	//U  - associated number - 3
	//U' - associated number - 4
	//R  - associated number - 5
	//R' - associated number - 6
	//D  - associated number - 7
	//D' - associated number - 8
	//L  - associated number - 9
	//L' - associated number - 10
	//0
	private void setupUp(){
		topFace.setNextMove(1);
		frontFace.setNextMove(3);
		rightFace.setNextMove(3);
		backFace.setNextMove(3);
		leftFace.setNextMove(3);
		bottemFace.setNextMove(0);
	}
	//1
	private void setupUpComp(){
		topFace.setNextMove(2);
		frontFace.setNextMove(4);
		rightFace.setNextMove(4);
		backFace.setNextMove(4);
		leftFace.setNextMove(4);
		bottemFace.setNextMove(0);
	}
	//2
	private void setupFront(){
		topFace.setNextMove(3);
		frontFace.setNextMove(1);
		rightFace.setNextMove(9);
		backFace.setNextMove(0);
		leftFace.setNextMove(5);
		bottemFace.setNextMove(3);
	}
	//3
	private void setupFrontComp(){
		topFace.setNextMove(4);
		frontFace.setNextMove(2);
		rightFace.setNextMove(10);
		backFace.setNextMove(0);
		leftFace.setNextMove(6);
		bottemFace.setNextMove(4);
	}
	//4
	private void setupRight(){
		topFace.setNextMove(9);
		frontFace.setNextMove(5);
		rightFace.setNextMove(1);
		backFace.setNextMove(9);
		leftFace.setNextMove(0);
		bottemFace.setNextMove(5);
	}
	//5
	private void setupRightComp(){
		topFace.setNextMove(10);
		frontFace.setNextMove(6);
		rightFace.setNextMove(2);
		backFace.setNextMove(10);
		leftFace.setNextMove(0);
		bottemFace.setNextMove(6);
	}
	//6
	private void setupBack(){
		topFace.setNextMove(7);
		frontFace.setNextMove(0);
		rightFace.setNextMove(5);
		backFace.setNextMove(1);
		leftFace.setNextMove(9);
		bottemFace.setNextMove(7);
	}
	//7
	private void setupBackComp(){
		topFace.setNextMove(8);
		frontFace.setNextMove(0);
		rightFace.setNextMove(6);
		backFace.setNextMove(2);
		leftFace.setNextMove(10);
		bottemFace.setNextMove(8);
	}
	//8
	private void setupLeft(){
		topFace.setNextMove(5);
		frontFace.setNextMove(9);
		rightFace.setNextMove(0);
		backFace.setNextMove(5);
		leftFace.setNextMove(1);
		bottemFace.setNextMove(9);
	}
	//9
	private void setupLeftComp(){
		topFace.setNextMove(6);
		frontFace.setNextMove(10);
		rightFace.setNextMove(0);
		backFace.setNextMove(6);
		leftFace.setNextMove(2);
		bottemFace.setNextMove(10);
	}
	//10
	private void setupBottom(){
		topFace.setNextMove(0);
		frontFace.setNextMove(7);
		rightFace.setNextMove(7);
		backFace.setNextMove(7);
		leftFace.setNextMove(7);
		bottemFace.setNextMove(1);
	}
	//11
	private void setupBottomComp(){
		topFace.setNextMove(0);
		frontFace.setNextMove(8);
		rightFace.setNextMove(8);
		backFace.setNextMove(8);
		leftFace.setNextMove(8);
		bottemFace.setNextMove(2);
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Perception Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Display Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Prints all the faces
	public void printFaces(){
		System.out.println("topFace\n");
		topFace.printFace();
		System.out.println("frontFace\n");
		frontFace.printFace();
		System.out.println("rightFace\n");
		rightFace.printFace();
		System.out.println("backFace\n");
		backFace.printFace();
		System.out.println("leftFace\n");
		leftFace.printFace();
		System.out.println("bottomFace\n");
		bottemFace.printFace();
	}
	public void printFN(){
		frontFace.printNeighbors();
	}
	// public void printCube(){
	// 	//should orient them in a way that shows the coninuity of the cube
	// }

	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Equality Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean solvedCube(){
		return (topFace.solvedFace()&&frontFace.solvedFace()&&rightFace.solvedFace()&&backFace.solvedFace()&&leftFace.solvedFace()&&bottemFace.solvedFace());
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
