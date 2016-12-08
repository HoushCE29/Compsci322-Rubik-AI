import cube.RubiksCube;


public class Move {
	private RubiksCube state;
	private int score;
	public Move(RubiksCube state,int score){
		this.state=state;
		this.score=score;
	}
	public Move(RubiksCube state){
		this.state=state;
		this.score=state.misplaced();
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
	
}
