import states.Rotation;
import cube.RubiksCube;
import java.util.*;

public class Test {

	public static void main(String...args){
		RubiksCube rc = new RubiksCube(10);
		System.out.println("Starting State:");
		System.out.println(rc.toString());
		//int min=Integer.MAX_VALUE;
		//Left Rotate Clockwise
		int turn=0;
		while(!rc.isSolved() && turn <20){
			ArrayList <Move> moves=new ArrayList<Move>();
			RubiksCube LRCW=rc.clone();
			int move=-1;//integer representing the move the system will make
			LRCW.rotateLeft(Rotation.CLOCK);
			//int LRCWScore=LRCW.misplaced();
			moves.add(new Move(LRCW,1));
			/*if(LRCWScore < min){
				min=LRCWScore;
				move=0;
			}*/
			
			//Left Rotate Counter-Clockwise
			RubiksCube LRCCW=rc.clone();
			LRCCW.rotateLeft(Rotation.COUNTER);
			//int LRCCWScore=LRCCW.misplaced();
			moves.add(new Move(LRCCW,2));
			/*if(LRCCWScore<min){
				min=LRCCWScore;
				move=1;
			}*/
			//Right Rotate Clockwise
			RubiksCube RRCW=rc.clone();
			RRCW.rotateRight(Rotation.CLOCK);
			//int RRCWScore=RRCW.misplaced();
			moves.add(new Move(RRCW,3));
			/*if(RRCWScore<min){
				min=RRCWScore;
				move=2;
			}*/
			//Right Rotate Counter-Clockwise
			RubiksCube RRCCW=rc.clone();
			RRCCW.rotateRight(Rotation.COUNTER);
			//int RRCCWScore=RRCCW.misplaced();
			moves.add(new Move(RRCCW,4));
			/*if(RRCCWScore<min){
				min=RRCCWScore;
				move=3;
			}*/
			//Top Rotate Clockwise
			RubiksCube TRCW=rc.clone();
			TRCW.rotateTop(Rotation.CLOCK);
			//int TRCWScore=TRCW.misplaced();
			moves.add(new Move(TRCW,5));
			/*if(TRCWScore<min){
				min=TRCWScore;
				move=4;
			}*/
			//Top Rotate Counter-Clockwise
			RubiksCube TRCCW=rc.clone();
			TRCCW.rotateTop(Rotation.COUNTER);
			//int TRCCWScore=TRCCW.misplaced();
			moves.add(new Move(TRCCW,6));
			/*if(TRCCWScore<min){
				min=TRCCWScore;
				move=5;
			}*/
			//Bottom Rotate Clockwise
			RubiksCube BotRCW=rc.clone();
			BotRCW.rotateBottom(Rotation.CLOCK);
			//int BotRCWScore=BotRCW.misplaced();
			moves.add(new Move(BotRCW,7));
			/*if(BotRCWScore<min){
				min=BotRCWScore;
				move=6;
			}*/
			//Bottom Rotate Counter-Clockwise
			RubiksCube BotRCCW=rc.clone();
			BotRCCW.rotateBottom(Rotation.COUNTER);
			//int BotRCCWScore=BotRCCW.misplaced();
			moves.add(new Move(BotRCCW,8));
			/*if(BotRCCWScore<min){
				min=BotRCCWScore;
				move=7;
			}*/
			//Front Rotate Clockwise
			RubiksCube FRCW=rc.clone();
			FRCW.rotateFront(Rotation.COUNTER);
			//int FRCWScore=FRCW.misplaced();
			moves.add(new Move(FRCW,9));
			/*if(FRCWScore<min){
				min=FRCWScore;
				move=8;
			}*/
			//Front Rotate Counter Clockwise
			RubiksCube FRCCW=rc.clone();
			FRCCW.rotateFront(Rotation.COUNTER);
			//int FRCCWScore=FRCCW.misplaced();
			moves.add(new Move(FRCCW,10));
			/*if(FRCCWScore<min){
				min=FRCCWScore;
				move=9;
			}*/
			//Back Rotate Clockwise
			RubiksCube BackRCW=rc.clone();
			BackRCW.rotateBack(Rotation.COUNTER);
			//int BackRCWScore=BackRCW.misplaced();
			moves.add(new Move(BackRCW,11));
			/*if(BackRCWScore<min){
				min=BackRCWScore;
				move=10;
			}*/
			//Back Rotate Counter Clockwise
			RubiksCube BackRCCW=rc.clone();
			BackRCCW.rotateBack(Rotation.COUNTER);
			//int BackRCCWScore=BackRCCW.misplaced();
			moves.add(new Move(BackRCCW,12));
			/*if(BackRCCWScore<min){
				min=BackRCCWScore;
				move=11;
			}*/
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
			/*System.out.println("Scores:");
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
			System.out.println("Move 11:"+BackRCCWScore);*/
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
			/*while((((move==1 && rc.getPreviousMoveID()==2)||
				   (move==2 && rc.getPreviousMoveID()==1)||
				   (move==3 && rc.getPreviousMoveID()==4)||
				   (move==4 && rc.getPreviousMoveID()==3)||
				   (move==5 && rc.getPreviousMoveID()==6)||
				   (move==6 && rc.getPreviousMoveID()==5)||
				   (move==7 && rc.getPreviousMoveID()==8)||
				   (move==8 && rc.getPreviousMoveID()==7)||
				   (move==9 && rc.getPreviousMoveID()==10)||
				   (move==10 && rc.getPreviousMoveID()==9)||
				   (move==11 && rc.getPreviousMoveID()==12)||
				   (move==12 && rc.getPreviousMoveID()==11)))&&
				   (checkState.isSame(rc.getPreviousState1())||checkState.isSame(rc.getPreviousState2())));*/
			//while();
			System.out.println("Going with move "+move);
			checkState.isSame(rc.getPreviousState1());
			rc.move(move);
			/*switch(move){
				case 1:rc.rotateLeft(Rotation.CLOCK);break;//LRCW
				case 2:rc.rotateLeft(Rotation.COUNTER);break;//LRCCW
				case 3:rc.rotateRight(Rotation.CLOCK);break;//RRCW
				case 4:rc.rotateRight(Rotation.COUNTER);break;//RRCCW
				case 5:rc.rotateTop(Rotation.CLOCK);break;//TRCW
				case 6:rc.rotateTop(Rotation.COUNTER);break;//TRCCW
				case 7:rc.rotateBottom(Rotation.CLOCK);break;//BotRCW
				case 8:rc.rotateBottom(Rotation.COUNTER);break;//BotRCCW
				case 9:rc.rotateFront(Rotation.CLOCK);break;//FRCW
				case 10:rc.rotateFront(Rotation.COUNTER);break;//FRCCW
				case 11:rc.rotateBack(Rotation.CLOCK);break;//BackRCW
				case 12:rc.rotateBack(Rotation.COUNTER);break;//BackRCCW
			}*/
			System.out.println(rc.toString());
			turn++;
			//System.out.println(rc.getPreviousState1().toString());
		}
	}
}