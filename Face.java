//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//To do
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Class Variables
// -None
// Constructor Methods
// -None
// Accessor Methods
// -None
// Modifier Methods
// -None
// Movement Control Methods
// -None
// Movement Methods
// -None
// Output Methods
// -None
// Equality Methods
// -None

public class Face{

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Class Variables
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private int color;				//Holds the initial solved color of the side
	private int[][] squares;		//Holds all the square values
	private Face aboveFace;
	private Face rightFace;
	private Face belowFace;
	private Face leftFace;
	private int nextMove;			//Holds the next move relative to this face in the form of a number 0-10
	private int[] outSquares;		//Holds the squares that will be leave after a single move in the order of first out


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructor Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Default
	public Face(){
		setColor(0);
		aboveFace = new Face();
		rightFace = new Face();
		belowFace = new Face();
		leftFace = new Face();
		nextMove = 0;
		outSquares = new int[3];
		outSquares[0] = 0;
		outSquares[1] = 0;
		outSquares[2] = 0;
		// outSquares = new int[3];
	}
	//Color input
	public Face(int a){
		setColor(a);
		aboveFace = new Face();
		rightFace = new Face();
		belowFace = new Face();
		leftFace = new Face();
		nextMove = 0;
		outSquares = new int[3];
		outSquares[0] = 0;
		outSquares[1] = 0;
		outSquares[2] = 0;
		// outSquares = new int[3];
	}
	//Adjacent Face input
	public Face(Face up, Face right, Face down, Face left){
		setColor(0);
		aboveFace = up;
		rightFace = right;
		belowFace = down;
		leftFace = left;
		nextMove = 0;
		outSquares = new int[3];
		outSquares[0] = 0;
		outSquares[1] = 0;
		outSquares[2] = 0;
		// outSquares = new int[3];
	}
	//Color and Adjacent Face input
	public Face(int a, Face up, Face right, Face down, Face left){
		setColor(a);
		aboveFace = up;
		rightFace = right;
		belowFace = down;
		leftFace = left;
		nextMove = 0;
		outSquares = new int[3];
		outSquares[0] = 0;
		outSquares[1] = 0;
		outSquares[2] = 0;
		// outSquares = new int[3];
	}
	public Face(int a, Face up, Face right, Face down, Face left, int[][] cloning){
		color = a;
		squares = cloning;
		aboveFace = up;
		rightFace = right;
		belowFace = down;
		leftFace = left;
		nextMove = 0;
		outSquares = new int[3];
		outSquares[0] = 0;
		outSquares[1] = 0;
		outSquares[2] = 0;
		// outSquares = new int[3];
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Accessor Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int getColor(){
		return color;
	}
	public int getSquare(int r, int c){
		return squares[r][c];
	}
	public int[][] getAllSquares(){
		return squares;
	}
	public Face clone(){
		return new Face(color,aboveFace,rightFace,belowFace,leftFace,squares);
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Modifier Methods for changing the 'color' of the side of the cube, either all at once, or just the middle square
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void setColor(int a){
		color = a;
		squares = new int[][]{
							{a,a,a},
							{a,a,a},
							{a,a,a}};
	}
	public void changeColor(int a){
		color = a;
		for(int r=0;r<3;r++)
			for(int c=0;c<3;c++)
				squares[r][c] = a;
	}
	public void setMiddle(int a){
		color = a;
		squares[1][1] = a;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Modifier Methods for changing adjecent Faces
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void setAllFaces(Face up, Face right, Face down, Face left){
		aboveFace = up;
		rightFace = right;
		belowFace = down;
		leftFace = left;
	}
	public void setAboveFace(Face up){
		aboveFace = up;
	}
	public void setRightFace(Face right){
		rightFace = right;
	}
	public void setBelowFace(Face down){
		belowFace = down;
	}
	public void setLeftFace(Face left){
		leftFace = left;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Movement Control Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Sets nextMove and calls an output setup based on nextMove
	public void setNextMove(int next){
		nextMove = next;
		//needs to set up output or call method to set up output
		switch(nextMove){
			case 3: setOutUp();
				break;
			case 4: setOutUpComp();
				break;
			case 5: setOutRight();
				break;
			case 6: setOutRightComp();
				break;
			case 7: setOutDown();
				break;
			case 8: setOutDownComp();
				break;
			case 9: setOutLeft();
				break;
			case 10: setOutLeftComp();
				break;
		}
	}
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
	public void move(){
		//switch statement or nested if statements that calls one of the move simulation methods
		switch(nextMove){
			case 1: moveFront();
				break;
			case 2: moveFrontComp();
				break;
			case 3: moveUp();
				break;
			case 4: moveUpComp();
				break;
			case 5: moveRight();
				break;
			case 6: moveRightComp();
				break;
			case 7: moveDown();
				break;
			case 8: moveDownComp();
				break;
			case 9: moveLeft();
				break;
			case 10: moveLeftComp();
				break;
		}
	}



	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Movement Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Simulate moves by requesting a 3x1 array from specifc face based on nextMove
	//Changes Squares based on called input from specific face
	//B *No Method*(this side does not move) - associated number - 0
	//F  - associated number - 1
	private void moveFront(){
		//Does not take any input
		int[] topRow = new int[3];
		int[] rightCol = new int[3];
		int[] botRow = new int[3];
		int[] leftCol = new int[3];
		//topRow
		topRow[0] = squares[0][2];
		topRow[1] = squares[0][1];
		topRow[2] = squares[0][0];
		//rightCol
		rightCol[0] = squares[2][2];
		rightCol[1] = squares[1][2];
		rightCol[2] = squares[0][2];
		//botRow
		botRow[0] = squares[2][0];
		botRow[1] = squares[2][1];
		botRow[2] = squares[2][2];
		//leftCol
		leftCol[0] = squares[0][0];
		leftCol[1] = squares[1][0];
		leftCol[2] = squares[2][0];
		//Setting
		//topRow
		squares[0][2] = leftCol[0];
		squares[0][1] = leftCol[1];
		squares[0][0] = leftCol[2];
		//rightCol
		squares[2][2] = topRow[0];
		squares[1][2] = topRow[1];
		squares[0][2] = topRow[2];
		//botRow
		squares[2][0] = rightCol[0];
		squares[2][1] = rightCol[1];
		squares[2][2] = rightCol[2];
		//leftCol
		squares[0][0] = botRow[0];
		squares[1][0] = botRow[1];
		squares[2][0] = botRow[2];
	}
	//F' - associated number - 2
	private void moveFrontComp(){
		//Does not take any input
		int[] topRow = new int[3];
		int[] rightCol = new int[3];
		int[] botRow = new int[3];
		int[] leftCol = new int[3];
		//topRow
		topRow[0] = squares[0][0];
		topRow[1] = squares[0][1];
		topRow[2] = squares[0][2];
		//rightCol
		rightCol[0] = squares[0][2];
		rightCol[1] = squares[1][2];
		rightCol[2] = squares[2][2];
		//botRow
		botRow[0] = squares[2][2];
		botRow[1] = squares[2][1];
		botRow[2] = squares[2][0];
		//leftCol
		leftCol[0] = squares[2][0];
		leftCol[1] = squares[1][0];
		leftCol[2] = squares[0][0];
		//Setting
		//topRow
		squares[0][0] = rightCol[0];
		squares[0][1] = rightCol[1];
		squares[0][2] = rightCol[2];
		//rightCol
		squares[0][2] = botRow[0];
		squares[1][2] = botRow[1];
		squares[2][2] = botRow[2];
		//botRow
		squares[2][2] = leftCol[0];
		squares[2][1] = leftCol[1];
		squares[2][0] = leftCol[2];
		//leftCol
		squares[2][0] = topRow[0];
		squares[1][0] = topRow[1];
		squares[0][0] = topRow[2];
	}
	//U  - associated number - 3
	private void moveUp(){
		int[] inSquares = rightFace.getOutput();
		squares[0][0] = inSquares[0];
		squares[0][1] = inSquares[1];
		squares[0][2] = inSquares[2];
	}
	//U' - associated number - 4
	private void moveUpComp(){
		int[] inSquares = leftFace.getOutput();
		squares[0][2] = inSquares[0];
		squares[0][1] = inSquares[1];
		squares[0][0] = inSquares[2];
	}
	//R  - associated number - 5
	private void moveRight(){
		int[] inSquares = belowFace.getOutput();
		squares[0][2] = inSquares[0];
		squares[1][2] = inSquares[1];
		squares[2][2] = inSquares[2];
	}
	//R' - associated number - 6
	private void moveRightComp(){
		int[] inSquares = aboveFace.getOutput();
		squares[2][2] = inSquares[0];
		squares[1][2] = inSquares[1];
		squares[0][2] = inSquares[2];
	}
	//D  - associated number - 7
	private void moveDown(){
		int[] inSquares = leftFace.getOutput();
		squares[2][2] = inSquares[0];
		squares[2][1] = inSquares[1];
		squares[2][0] = inSquares[2];
	}
	//D' - associated number - 8
	private void moveDownComp(){
		int[] inSquares = rightFace.getOutput();
		squares[2][0] = inSquares[0];
		squares[2][1] = inSquares[1];
		squares[2][2] = inSquares[2];
	}
	//L  - associated number - 9
	private void moveLeft(){
		int[] inSquares = aboveFace.getOutput();
		squares[2][0] = inSquares[0];
		squares[1][0] = inSquares[1];
		squares[0][0] = inSquares[2];
	}
	//L' - associated number - 10
	private void moveLeftComp(){
		int[] inSquares = belowFace.getOutput();
		squares[0][0] = inSquares[0];
		squares[1][0] = inSquares[1];
		squares[2][0] = inSquares[2];
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Output Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Provide a 3x1 array based on Next Move
	//Method called by other face objects
	public int[] getOutput(){
		return outSquares;
	}
	//Puts output in "first value out" order
	private void setOutUp(){
		outSquares[0] = squares[0][0];
		outSquares[1] = squares[0][1];
		outSquares[2] = squares[0][2];
	}
	private void setOutUpComp(){
		outSquares[0] = squares[0][2];
		outSquares[1] = squares[0][1];
		outSquares[2] = squares[0][0];
	}
	private void setOutRight(){
		outSquares[0] = squares[0][2];
		outSquares[1] = squares[1][2];
		outSquares[2] = squares[2][2];
	}
	private void setOutRightComp(){
		outSquares[0] = squares[2][2];
		outSquares[1] = squares[1][2];
		outSquares[2] = squares[0][2];
	}
	private void setOutDown(){
		outSquares[0] = squares[2][2];
		outSquares[1] = squares[2][1];
		outSquares[2] = squares[2][0];
	}
	private void setOutDownComp(){
		outSquares[0] = squares[2][0];
		outSquares[1] = squares[2][1];
		outSquares[2] = squares[2][2];
	}
	private void setOutLeft(){
		outSquares[0] = squares[2][0];
		outSquares[1] = squares[1][0];
		outSquares[2] = squares[0][0];
	}
	private void setOutLeftComp(){
		outSquares[0] = squares[0][0];
		outSquares[1] = squares[1][0];
		outSquares[2] = squares[2][0];
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Equality Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Compares all values in squares to the color
	public boolean solvedFace(){
		for(int r = 0;r<3;r++)
			for(int c = 0;c<3;c++)
				if(color != squares[r][c])
					return false;
		return true;
	}
	//Compares all values in squares to other values in a 
	public boolean equals(Face x){
		for(int r = 0;r<3;r++)
			for(int c = 0;c<3;c++)
				if(x.getSquare(r,c) != squares[r][c])
					return false;
		return true;	
	}


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Display Methods
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void printFace(){
		System.out.println(" "+squares[0][0]+" "+squares[0][1]+" "+squares[0][2]+"\n"+
						   " "+squares[1][0]+" "+squares[1][1]+" "+squares[1][2]+"\n"+
						   " "+squares[2][0]+" "+squares[2][1]+" "+squares[2][2]+"\n");
	}
	public void printNeighbors(){
		aboveFace.printFace();
		rightFace.printFace();
		belowFace.printFace();
		leftFace.printFace();
	}




	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}