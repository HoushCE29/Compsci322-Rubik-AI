import states.Rotation;
import cube.RubiksCube;
import java.util.*;

public class Test {

	public static void main(String...args){
		RubiksCube rc = new RubiksCube(10);
		System.out.println("Starting State:");
		System.out.println(rc.toString());
		System.out.print("Input number of moves: ");
		int maxMoves = (new Scanner(System.in)).nextInt();
		
		ArrayList<Integer> history = new ArrayList<>();//Tracks moves
		
		int turn=0;
		while(!rc.isSolved() && turn < maxMoves){
			ArrayList <Move> moves=new ArrayList<Move>();
			int move=-1;//integer representing the move the system will make
			
			//Left Rotate Clockwise
			RubiksCube LRCW=rc.clone();
			LRCW.rotateLeft(Rotation.CLOCK);
			moves.add(new Move(LRCW,1));

			
			//Left Rotate Counter-Clockwise
			RubiksCube LRCCW=rc.clone();
			LRCCW.rotateLeft(Rotation.COUNTER);
			moves.add(new Move(LRCCW,2));

			//Right Rotate Clockwise
			RubiksCube RRCW=rc.clone();
			RRCW.rotateRight(Rotation.CLOCK);
			moves.add(new Move(RRCW,3));

			//Right Rotate Counter-Clockwise
			RubiksCube RRCCW=rc.clone();
			RRCCW.rotateRight(Rotation.COUNTER);
			moves.add(new Move(RRCCW,4));

			//Top Rotate Clockwise
			RubiksCube TRCW=rc.clone();
			TRCW.rotateTop(Rotation.CLOCK);
			moves.add(new Move(TRCW,5));

			//Top Rotate Counter-Clockwise
			RubiksCube TRCCW=rc.clone();
			TRCCW.rotateTop(Rotation.COUNTER);
			moves.add(new Move(TRCCW,6));

			//Bottom Rotate Clockwise
			RubiksCube BotRCW=rc.clone();
			BotRCW.rotateBottom(Rotation.CLOCK);
			moves.add(new Move(BotRCW,7));

			//Bottom Rotate Counter-Clockwise
			RubiksCube BotRCCW=rc.clone();
			BotRCCW.rotateBottom(Rotation.COUNTER);
			moves.add(new Move(BotRCCW,8));

			//Front Rotate Clockwise
			RubiksCube FRCW=rc.clone();
			FRCW.rotateFront(Rotation.CLOCK);
			moves.add(new Move(FRCW,9));

			//Front Rotate Counter Clockwise
			RubiksCube FRCCW=rc.clone();
			FRCCW.rotateFront(Rotation.COUNTER);
			moves.add(new Move(FRCCW,10));

			//Back Rotate Clockwise
			RubiksCube BackRCW=rc.clone();
			BackRCW.rotateBack(Rotation.CLOCK);
			moves.add(new Move(BackRCW,11));
			
			//Back Rotate Counter Clockwise
			RubiksCube BackRCCW=rc.clone();
			BackRCCW.rotateBack(Rotation.COUNTER);
			moves.add(new Move(BackRCCW,12));

			for(int i=0;i<moves.size()-1;i++){
				if(((Move)(moves.get(i))).getScore()>((Move)(moves.get(i+1))).getScore()){
					Move temp=(Move)moves.get(i+1);
					moves.set(i+1, (Move)moves.get(i));
					moves.set(i,temp);
					i=-1;
				}
			}
			for(int i=0;i<moves.size()-1;i++){
				Move reading=(Move)moves.get(i);
				System.out.println("Score for moveID "+reading.getMoveID()+": "+reading.getScore());
			}

			
			System.out.println("Previous move ID="+rc.getPreviousMoveID());
			RubiksCube checkState;
			int iterator=0;
			do{
				checkState=((Move)(moves.get(iterator))).getState();
				move=((Move)(moves.get(iterator))).getMoveID();
				System.out.println("Evaluating Move " +move);
				iterator++;
				System.out.println(checkState.isSame(rc.getPreviousState1()));
				System.out.println(checkState.isSame(rc.getPreviousState2()));
			}while(checkState.isSame(rc.getPreviousState1())||checkState.isSame(rc.getPreviousState2()));

			move = moves.get(0).getMoveID();
			/*loop identification:
			 * *same move 3 times
			 * *moveID + 1 for odds (undos progress)
			 * *moveID - 1 for evens (undos progress)
			 */
			for(int i = 1; i < 12; i++){
				if(history.size() < 3){break;}
				if((move == history.get(history.size()-1)&&move == history.get(history.size()-2)&&move == history.get(history.size()-3))
						||(move%2==1&&move+1==history.get(history.size()-1))||(move%2==0&&move-1==history.get(history.size()-1))){
					move = moves.get(i).getMoveID();
				}
				else{break;}
			}
			System.out.println("Going with move "+move);
			checkState.isSame(rc.getPreviousState1());
			history.add(move);
			rc.move(move);

			System.out.println(rc.toString());
			turn++;
		}
		System.out.println("Moves for solving: ");
		for(Integer i: history){System.out.println(" * " + i.intValue());}
	}
}
