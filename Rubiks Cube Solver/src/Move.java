import cube.RubiksCube;


public class Move {
	private RubiksCube state;
	private int score;
	private int moveID;
	public Move(RubiksCube state,int score,int moveID){
		this.state=state;
		this.score=score;
		this.moveID=moveID;
	}
	public Move(RubiksCube state,int moveID){
		this.state=state;
		this.score=state.misplaced();
		this.moveID=moveID;
	}
	public Move(RubiksCube state){
		this.state=state;
		this.score=state.misplaced();
		this.moveID=-1;
	}
	public RubiksCube getState() {
		return state;
	}
	public void setState(RubiksCube state) {
		this.state = state;
	}
	public int getScore() {
		return score;
	}
	public int getMoveID(){
		return moveID;
	}
	
	
}