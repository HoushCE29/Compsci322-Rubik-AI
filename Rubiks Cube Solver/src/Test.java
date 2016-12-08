import states.Rotation;
import cube.RubiksCube;
import java.util.*;

public class Test {

	public static void main(String...args){
		RubiksCube rc = new RubiksCube(10);
		System.out.println("Starting State:");
		System.out.println(rc.toString());
		//while(!rc.isSolved()){
			int min=Integer.MAX_VALUE;
			ArrayList <Move> moves=new ArrayList<Move>();
			//Left Rotate Clockwise
			RubiksCube LRCW=rc.clone();
			int move=-1;//integer representing the move the system will make
			LRCW.rotateLeft(Rotation.CLOCK);
			int LRCWScore=LRCW.misplaced();
			moves.add(new Move(LRCW));
			/*if(LRCWScore < min){
				min=LRCWScore;
				move=0;
			}*/
			
			//Left Rotate Counter-Clockwise
			RubiksCube LRCCW=rc.clone();
			LRCCW.rotateLeft(Rotation.COUNTER);
			int LRCCWScore=LRCCW.misplaced();
			moves.add(new Move(LRCCW));
			/*if(LRCCWScore<min){
				min=LRCCWScore;
				move=1;
			}*/
			//Right Rotate Clockwise
			RubiksCube RRCW=rc.clone();
			RRCW.rotateLeft(Rotation.CLOCK);
			int RRCWScore=RRCW.misplaced();
			moves.add(new Move(RRCW));
			/*if(RRCWScore<min){
				min=RRCWScore;
				move=2;
			}*/
			//Right Rotate Counter-Clockwise
			RubiksCube RRCCW=rc.clone();
			RRCCW.rotateLeft(Rotation.COUNTER);
			int RRCCWScore=RRCCW.misplaced();
			moves.add(new Move(RRCCW));
			/*if(RRCCWScore<min){
				min=RRCCWScore;
				move=3;
			}*/
			//Top Rotate Clockwise
			RubiksCube TRCW=rc.clone();
			TRCW.rotateLeft(Rotation.CLOCK);
			int TRCWScore=TRCW.misplaced();
			moves.add(new Move(TRCW));
			/*if(TRCWScore<min){
				min=TRCWScore;
				move=4;
			}*/
			//Top Rotate Counter-Clockwise
			RubiksCube TRCCW=rc.clone();
			TRCCW.rotateLeft(Rotation.COUNTER);
			int TRCCWScore=TRCCW.misplaced();
			moves.add(new Move(TRCCW));
			/*if(TRCCWScore<min){
				min=TRCCWScore;
				move=5;
			}*/
			//Bottom Rotate Clockwise
			RubiksCube BotRCW=rc.clone();
			BotRCW.rotateLeft(Rotation.CLOCK);
			int BotRCWScore=BotRCW.misplaced();
			moves.add(new Move(BotRCW));
			/*if(BotRCWScore<min){
				min=BotRCWScore;
				move=6;
			}*/
			//Bottom Rotate Counter-Clockwise
			RubiksCube BotRCCW=rc.clone();
			BotRCCW.rotateLeft(Rotation.COUNTER);
			int BotRCCWScore=BotRCCW.misplaced();
			moves.add(new Move(BotRCCW));
			/*if(BotRCCWScore<min){
				min=BotRCCWScore;
				move=7;
			}*/
			//Front Rotate Clockwise
			RubiksCube FRCW=rc.clone();
			FRCW.rotateLeft(Rotation.COUNTER);
			int FRCWScore=FRCW.misplaced();
			moves.add(new Move(FRCW));
			/*if(FRCWScore<min){
				min=FRCWScore;
				move=8;
			}*/
			//Front Rotate Counter Clockwise
			RubiksCube FRCCW=rc.clone();
			FRCCW.rotateLeft(Rotation.COUNTER);
			int FRCCWScore=FRCCW.misplaced();
			moves.add(new Move(FRCCW));
			/*if(FRCCWScore<min){
				min=FRCCWScore;
				move=9;
			}*/
			//Back Rotate Clockwise
			RubiksCube BackRCW=rc.clone();
			BackRCW.rotateLeft(Rotation.COUNTER);
			int BackRCWScore=BackRCW.misplaced();
			moves.add(new Move(BackRCW));
			/*if(BackRCWScore<min){
				min=BackRCWScore;
				move=10;
			}*/
			//Back Rotate Counter Clockwise
			RubiksCube BackRCCW=rc.clone();
			BackRCCW.rotateLeft(Rotation.COUNTER);
			int BackRCCWScore=BackRCCW.misplaced();
			moves.add(new Move(BackRCCW));
			/*if(BackRCCWScore<min){
				min=BackRCCWScore;
				move=11;
			}*/
			System.out.println("Scores:");
			System.out.println("Move 0:"+LRCWScore);
			System.out.println("Move 1:"+LRCCWScore);
			System.out.println("Move 2:"+RRCWScore);
			System.out.println("Move 3:"+RRCCWScore);
			System.out.println("Move 4:"+TRCWScore);
			System.out.println("Move 5:"+TRCCWScore);
			System.out.println("Move 6:"+BotRCWScore);
			System.out.println("Move 7:"+BotRCCWScore);
			System.out.println("Move 8:"+FRCWScore);
			System.out.println("Move 9:"+FRCCWScore);
			System.out.println("Move 10:"+BackRCWScore);
			System.out.println("Move 11:"+BackRCCWScore);
			switch(move){
				case 0:rc.rotateLeft(Rotation.CLOCK);break;//LRCW
				case 1:rc.rotateLeft(Rotation.COUNTER);break;//LRCCW
				case 2:rc.rotateRight(Rotation.CLOCK);break;//RRCW
				case 3:rc.rotateRight(Rotation.COUNTER);break;//RRCCW
				case 4:rc.rotateTop(Rotation.CLOCK);break;//TRCW
				case 5:rc.rotateTop(Rotation.COUNTER);break;//TRCCW
				case 6:rc.rotateBottom(Rotation.CLOCK);break;//BotRCW
				case 7:rc.rotateBottom(Rotation.COUNTER);break;//BotRCCW
				case 8:rc.rotateFront(Rotation.CLOCK);break;//FRCW
				case 9:rc.rotateFront(Rotation.COUNTER);break;//FRCCW
				case 10:rc.rotateBack(Rotation.CLOCK);break;//BackRCW
				case 11:rc.rotateBack(Rotation.COUNTER);break;//BackRCCW
			}
			
			System.out.println("Going with move "+move);
			System.out.println(rc.toString());
		//}
	}
}
