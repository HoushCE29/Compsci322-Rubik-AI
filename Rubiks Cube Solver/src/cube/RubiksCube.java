package cube;

import states.Color;
import states.Rotation;

/*
 * NOTES:
 * 
 * How to visual the cube in context with this class:
 * 
 *  Colors&Positions = {G->Green = top, O->Orange = left, W->White = front, 
 *  		R->Red = right, Y->Yellow = back, B->Blue = bottom}
 *  
 *  Also, at this view, any rotation with the top of the face going in the direction of:
 *  
 *  >> Right ( ----> ) is considered clock[wise]
 *  >> Left ( <---- ) is considered counter[clockwise]
 *                  ___
 *              top| G |
 *              ___|___|___ ___
 *         left| O | W | R | Y |back
 *             |___|___|___|___|
 *                 | B |
 *                 |___|bottom
 *                 
 *                 
 * Each square/face in the above image will have the same indexing with respect
 * to its orientation shown, such that the top left corner of each is (0, 0) and 
 * the bottom right is (2, 2).  
 * 
 * In other words, each face is decorated as follows (in 2D array format coordinates):
 * +------+------+------+
 * |[0][0]|[1][0]|[2][0]|
 * |      |      |      |
 * +------+------+------+
 * |[0][1]|[1][1]|[2][1]|
 * |      |      |      |
 * +------+------+------+
 * |[0][2]|[1][2]|[2][2]|
 * |      |      |      |
 * +------+------+------+
 * 
 * The output for console will look like this (as called from toString()):
 *                     +------+------+------+
                       |GRE   |GRE   |GRE   |
                       |      |      |      |
                       +------+------+------+
                       |GRE   |GRE   |GRE   |
                       |      |      |      |
                       +------+------+------+
                       |GRE   |GRE   |GRE   |
                       |      |      |      |
                       +------+------+------+
+------+------+------+ +------+------+------+ +------+------+------+ +------+------+------+
|ORA   |ORA   |ORA   | |WHI   |WHI   |WHI   | |RED   |RED   |RED   | |YEL   |YEL   |YEL   | 
|      |      |      | |      |      |      | |      |      |      | |      |      |      |
+------+------+------+ +------+------+------+ +------+------+------+ +------+------+------+
|ORA   |ORA   |ORA   | |WHI   |WHI   |WHI   | |RED   |RED   |RED   | |YEL   |YEL   |YEL   | 
|      |      |      | |      |      |      | |      |      |      | |      |      |      |
+------+------+------+ +------+------+------+ +------+------+------+ +------+------+------+
|ORA   |ORA   |ORA   | |WHI   |WHI   |WHI   | |RED   |RED   |RED   | |YEL   |YEL   |YEL   | 
|      |      |      | |      |      |      | |      |      |      | |      |      |      |
+------+------+------+ +------+------+------+ +------+------+------+ +------+------+------+
                       +------+------+------+
                       |BLU   |BLU   |BLU   |
                       |      |      |      |
                       +------+------+------+
                       |BLU   |BLU   |BLU   |
                       |      |      |      |
                       +------+------+------+
                       |BLU   |BLU   |BLU   |
                       |      |      |      |
                       +------+------+------+
 * 
 * 
 */
public class RubiksCube implements Cube, Cloneable{

	//Faces represented in 2D arrays
	private Color[][] top = Color.GREEN.face();
	private Color[][] left = Color.ORANGE.face();
	private Color[][] right = Color.RED.face();
	private Color[][] back = Color.YELLOW.face();
	private Color[][] bottom = Color.BLUE.face();
	private Color[][] front = Color.WHITE.face();
	private RubiksCube previousState1;
	private RubiksCube previousState2;
	private int previousMoveID=-1;
	//Easily accessible solved cube
	public static final RubiksCube SOLVED_CUBE = new RubiksCube(Color.WHITE.face(), 
			Color.GREEN.face(), Color.BLUE.face(), Color.ORANGE.face(), Color.RED.face(),
			Color.YELLOW.face());
	
	//Normal constructor - mixes cube using 20 random moves
	public RubiksCube(){
		for(int i = 0; i < 20; i++){
			Rotation rotate = Rotation.CLOCK;
			double r = Math.random()*2;
			if((int)r == 1)rotate = Rotation.COUNTER;
			double face = Math.random()*6;
			switch((int)face){
			case 0: 
				rotateTop(rotate);
				break;
			case 1:
				rotateLeft(rotate);
				break;
			case 2:
				rotateRight(rotate);
				break;
			case 3:
				rotateBack(rotate);
				break;
			case 4:
				rotateBottom(rotate);
				break;
			case 5:
				rotateFront(rotate);
				break;
			}
			previousState1=null;
			previousState2=null;
		}
	}
	
	//Constructor that mixes the cube with a certain number of moves
	public RubiksCube(int mixes){
		for(int i = 0; i < mixes; i++){
			Rotation rotate = Rotation.CLOCK;
			double r = Math.random()*2;
			if((int)r == 1)rotate = Rotation.COUNTER;
			double face = Math.random()*6;
			switch((int)face){
			case 0: 
				rotateTop(rotate);
				break;
			case 1:
				rotateLeft(rotate);
				break;
			case 2:
				rotateRight(rotate);
				break;
			case 3:
				rotateBack(rotate);
				break;
			case 4:
				rotateBottom(rotate);
				break;
			case 5:
				rotateFront(rotate);
				break;
			}
		}
	}
	
	//User defined constructor
	public RubiksCube(Color[][]front, Color[][]top, Color[][]bottom, 
			Color[][]left, Color[][]right, Color[][]back){
		this.front = front;
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		this.back = back;
	}
	
	@Override
	public Color[][] getFace(Color face) {
		switch(face){
		case WHITE: return front;
		case GREEN: return top;
		case BLUE: return bottom;
		case ORANGE: return left;
		case RED: return right;
		case YELLOW: return back;
		}
		return null;
	}
	
	public int getPreviousMoveID() {
		return previousMoveID;
	}

	public void setPreviousMoveID(int previousMoveID) {
		this.previousMoveID = previousMoveID;
	}

	/**
	 * Used to simplify all rotations, as all use this same algorithm.
	 * @param face Face to be rotated
	 * @param r Direction of rotation
	 */
	private void rotateSurface(Color[][]face, Rotation r){
		Color temp = Color.WHITE;
		switch(r){
		case CLOCK:
			//CORNERS
			temp = face[0][0];
			face[0][0] = face[0][2];
			face[0][2] = face[2][2];
			face[2][2] = face[2][0];
			face[2][0] = temp;
			//EDGES
			temp = face[1][0];
			face[1][0] = face[0][1];
			face[0][1] = face[1][2];
			face[1][2] = face[2][1];
			face[2][1] = temp;
			break;
		case COUNTER:
			//CORNERS
			temp = face[2][0];
			face[2][0] = face[2][2];
			face[2][2] = face[0][2];
			face[0][2] = face[0][0];
			face[0][0] = temp;
			//EDGES
			temp = face[2][1];
			face[2][1] = face[1][2];
			face[1][2] = face[0][1];
			face[0][1] = face[1][0];
			face[1][0] = temp;
		}
	}

	@Override
	public void rotateFront(Rotation r) {
		rotateSurface(front, r);
		Color temp[] = {top[0][2], top[1][2], top[2][2]};
		switch(r){
		case CLOCK:
			for(int i = 0; i < 3; i++){
				top[i][2] = left[2][2-i];
				left[2][2-i] = bottom[2-i][0];
				bottom[2-i][0] = right[0][i];
				right[0][i] = temp[i];
			}
			break;
		case COUNTER:
			for(int i = 0; i < 3; i++){
				top[i][2] = right[0][i];
				right[0][i] = bottom[2-i][0];
				bottom[2-i][0] = left[2][2-i];
				left[2][2-i] = temp[i];
			}
		}
	}

	@Override
	public void rotateBack(Rotation r) {
		rotateSurface(back, r);
		Color temp[] = {right[2][0], right[2][1], right[2][2]};
		switch(r){
		case CLOCK:
			for(int i = 0; i < 3; i++){
				right[2][i] = bottom[2-i][2];
				bottom[2-i][2] = left[0][2-i];
				left[0][2-i] = top[i][0];
				top[i][0] = temp[i];
			}
			break;
		case COUNTER:
			for(int i = 0; i < 3; i++){
				right[2][i] = top[i][0];
				top[i][0] = left[0][2-i];
				left[0][2-i] = bottom[2-i][2];
				bottom[2-i][2] = temp[i];
			}
		}
	}

	@Override
	public void rotateLeft(Rotation r) {
		rotateSurface(left, r);
		Color temp[] = {bottom[0][0], bottom[0][1], bottom[0][2]};
		switch(r){
		case CLOCK:
			for(int i = 0; i < 3; i++){
				bottom[0][i] = front[0][i];
				front[0][i] = top[0][i];
				top[0][i] = back[2][2-i];
				back[2][2-i] = temp[i];
			}
			break;
		case COUNTER:
			for(int i = 0; i < 3; i++){
				bottom[0][i] = back[2][2-i];
				back[2][2-i] = top[0][i];
				top[0][i] = front[0][i];
				front[0][i] = temp[i];
			}
		}
	}

	@Override
	public void rotateRight(Rotation r) {
		rotateSurface(right, r);
		Color temp[] = {bottom[2][0], bottom[2][1], bottom[2][2]};
		switch(r){
		case CLOCK:
			for(int i = 0; i < 3; i++){
				bottom[2][i] = back[0][2-i];
				back[0][2-i] = top[2][i];
				top[2][i] = front[2][i];
				front[2][i] = temp[i];
			}
			break;
		case COUNTER:
			for(int i = 0; i < 3; i++){
				bottom[2][i] = front[2][i];
				front[2][i] = top[2][i];
				top[2][i] = back[0][2-i];
				back[0][2-i] = temp[i];
			}
		}
	}

	@Override
	public void rotateTop(Rotation r) {
		rotateSurface(top, r);
		Color temp[] = {back[0][0], back[1][0], back[2][0]};
		switch(r){
		case CLOCK:
			for(int i = 0; i < 3; i++){
				back[i][0] = left[i][0];
				left[i][0] = front[i][0];
				front[i][0] = right[i][0];
				right[i][0] = temp[i];
			}
			break;
		case COUNTER:
			for(int i = 0; i < 3; i++){
				back[i][0] = right[i][0];
				right[i][0] = front[i][0];
				front[i][0] = left[i][0];
				left[i][0] = temp[i];
			}
		}
	}

	@Override
	public void rotateBottom(Rotation r) {
		rotateSurface(bottom, r);
		Color temp[] = {back[0][2], back[1][2], back[2][2]};
		switch(r){
		case CLOCK:
			for(int i = 0; i < 3; i++){
				back[i][2] = right[i][2];
				right[i][2] = front[i][2];
				front[i][2] = left[i][2];
				left[i][2] = temp[i];
			}
			break;
		case COUNTER:
			for(int i = 0; i < 3; i++){
				back[i][2] = left[i][2];
				left[i][2] = front[i][2];
				front[i][2] = right[i][2];
				right[i][2] = temp[i];
			}
		}
	}

	@Override
	public boolean isSolved() {
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				if(x == 1 && y == 1)continue;
				if(!left[x][y].equals(Color.ORANGE) || !front[x][y].equals(Color.WHITE)
						|| !right[x][y].equals(Color.RED) || !back[x][y].equals(Color.YELLOW)
						|| !top[x][y].equals(Color.GREEN) || !bottom[x][y].equals(Color.BLUE)){return false;}
			}
		}
		return true;
	}
	
	/**
	 * Returns the number of misplaced tiles in this cube.
	 * @return Number of misplaced tiles.
	 */
	public int misplaced(){
		int count = 0;
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				if(x == 1 && y == 1)continue;
				if(!left[x][y].equals(Color.ORANGE))count++;
				if(!front[x][y].equals(Color.WHITE))count++;
				if(!right[x][y].equals(Color.RED))count++;
				if(!back[x][y].equals(Color.YELLOW))count++;
				if(!top[x][y].equals(Color.GREEN))count++;
				if(!bottom[x][y].equals(Color.BLUE))count++;
			}
		}
		return count;
	}
	
	@Override
	public String toString(){
		final String vert = "+------+------+------+"; //border
		final String indent = "                       "; //indent
		final String blank = "|      |      |      |"; //spaces
		String display = indent + vert + "\r\n" + indent;
		//GREEN-TOP FACE
		for(int y = 0; y < 3; y++){
			for(int x = 0; x < 3; x++){
				display += "|" + top[x][y].code() + "   ";
			}
			display += "|\r\n" + indent + blank + "\r\n" + indent + vert + "\r\n";
			if(y!=2)display += indent;
		}
		display += vert + " " + vert + " " + vert + " " + vert + "\r\n";
		//ORANGE-LEFT + WHITE-FRONT + RED-RIGHT + YELLOW-BACK
		for(int y = 0; y < 3; y++){// y - coor
			for(int f = 0; f < 4; f++){ // face id
				Color[][]face = left;
				switch(f){
				case 0: face = left; break;
				case 1: face = front; break;
				case 2: face = right; break;
				case 3: face = back; break;
				}
				for(int x = 0; x < 3; x++){ // x- coor
					display += "|" + face[x][y].code() + "   ";
				}
				display += "| ";
			}
			display += "\r\n" + blank + " " + blank + " " + blank + " " + blank + "\r\n" + 
			vert + " " + vert + " " + vert + " " + vert + "\r\n";
		}
		display += indent + vert + "\r\n" + indent;
		//BLUE-BOTTOM FACE
		for(int y = 0; y < 3; y++){
			for(int x = 0; x < 3; x++){
				display += "|" + bottom[x][y].code() + "   ";
			}
			display += "|\r\n" + indent + blank + "\r\n" + indent + vert + "\r\n" + indent;
		}
		display += "\r\n---";
		return display;
	}
	
	//Prevents pass by reference effects for determining possible moves
	@Override
	public RubiksCube clone(){
		Color[][]cTop = new Color[3][3];
		Color[][]cBottom = new Color[3][3];
		Color[][]cLeft = new Color[3][3];
		Color[][]cRight = new Color[3][3];
		Color[][]cBack = new Color[3][3];
		Color[][]cFront = new Color[3][3];
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				cTop[x][y] = top[x][y];
				cBottom[x][y] = bottom[x][y];
				cLeft[x][y] = left[x][y];
				cRight[x][y] = right[x][y];
				cBack[x][y] = back[x][y];
				cFront[x][y] = front[x][y];
			}
		}
		return new RubiksCube(cFront, cTop, cBottom, cLeft, cRight, cBack);
	}
	public RubiksCube getPreviousState1(){
		return previousState1;
	}
	public RubiksCube getPreviousState2(){
		return previousState2;
	}
	public void move(int moveID){
		previousState2=previousState1;
		previousState1=this.clone();
		switch(moveID){
			case 1:this.rotateLeft(Rotation.CLOCK);break;//LRCW
			case 2:this.rotateLeft(Rotation.COUNTER);break;//LRCCW
			case 3:this.rotateRight(Rotation.CLOCK);break;//RRCW
			case 4:this.rotateRight(Rotation.COUNTER);break;//RRCCW
			case 5:this.rotateTop(Rotation.CLOCK);break;//TRCW
			case 6:this.rotateTop(Rotation.COUNTER);break;//TRCCW
			case 7:this.rotateBottom(Rotation.CLOCK);break;//BotRCW
			case 8:this.rotateBottom(Rotation.COUNTER);break;//BotRCCW
			case 9:this.rotateFront(Rotation.CLOCK);break;//FRCW
			case 10:this.rotateFront(Rotation.COUNTER);break;//FRCCW
			case 11:this.rotateBack(Rotation.CLOCK);break;//BackRCW
			case 12:this.rotateBack(Rotation.COUNTER);break;//BackRCCW
		}
		previousMoveID=moveID;
		
	}
	public boolean isSame(RubiksCube comparing){
		boolean sameness=true;
		if(comparing==null){
			return false;
		}
		else{
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(!this.getFace(Color.BLUE)[i][j].equals(comparing.getFace(Color.BLUE)[i][j])
					 ||!this.getFace(Color.WHITE)[i][j].equals(comparing.getFace(Color.WHITE)[i][j])
					 ||this.getFace(Color.ORANGE)[i][j]!=comparing.getFace(Color.ORANGE)[i][j]
					 ||this.getFace(Color.RED)[i][j]!=comparing.getFace(Color.RED)[i][j]
					 ||this.getFace(Color.GREEN)[i][j]!=comparing.getFace(Color.GREEN)[i][j]
					 ||this.getFace(Color.YELLOW)[i][j]!=comparing.getFace(Color.YELLOW)[i][j]){
						sameness=false;
					}
				}
			}
		}
		return sameness;
	}
}
