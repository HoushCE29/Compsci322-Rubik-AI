import states.Rotation;
import cube.RubiksCube;


public class Test {

	public static void main(String...args){
		RubiksCube rc = new RubiksCube(10);
		System.out.println(rc.toString());
		int min=Integer.MAX_VALUE;
		//Left Rotate Clockwise
		RubiksCube LRCW=rc.clone();
		int move=-1;//integer representing the move the system will make
		LRCW.rotateLeft(Rotation.CLOCK);
		int LRCWScore=LRCW.misplaced();
		if(LRCWScore < min){
			min=LRCWScore;
			move=0;
		}
		//Left Rotate Counter-Clockwise
		RubiksCube LRCCW=rc.clone();
		LRCCW.rotateLeft(Rotation.COUNTER);
		int LRCCWScore=LRCCW.misplaced();
		if(LRCCWScore<min){
			min=LRCCWScore;
			move=1;
		}
		//Right Rotate Clockwise
		RubiksCube RRCW=rc.clone();
		RRCW.rotateLeft(Rotation.CLOCK);
		int RRCWScore=RRCW.misplaced();
		if(RRCWScore<min){
			min=RRCWScore;
			move=2;
		}
		//Right Rotate Counter-Clockwise
		RubiksCube RRCCW=rc.clone();
		RRCCW.rotateLeft(Rotation.COUNTER);
		int RRCCWScore=RRCCW.misplaced();
		if(RRCCWScore<min){
			min=RRCCWScore;
			move=3;
		}
		//Top Rotate Clockwise
		RubiksCube TRCW=rc.clone();
		TRCW.rotateLeft(Rotation.CLOCK);
		int TRCWScore=TRCW.misplaced();
		if(TRCWScore<min){
			min=TRCWScore;
			move=4;
		}
		//Top Rotate Counter-Clockwise
		RubiksCube TRCCW=rc.clone();
		TRCCW.rotateLeft(Rotation.COUNTER);
		int TRCCWScore=TRCCW.misplaced();
		if(TRCCWScore<min){
			min=TRCCWScore;
			move=5;
		}
		//Bottom Rotate Clockwise
		RubiksCube BotRCW=rc.clone();
		BotRCW.rotateLeft(Rotation.CLOCK);
		int BotRCWScore=BotRCW.misplaced();
		if(BotRCWScore<min){
			min=BotRCWScore;
			move=6;
		}
		//Bottom Rotate Counter-Clockwise
		RubiksCube BotRCCW=rc.clone();
		BotRCCW.rotateLeft(Rotation.COUNTER);
		int BotRCCWScore=BotRCCW.misplaced();
		if(BotRCCWScore<min){
			min=BotRCCWScore;
			move=7;
		}
		//Front Rotate Clockwise
		RubiksCube FRCW=rc.clone();
		FRCW.rotateLeft(Rotation.COUNTER);
		int FRCWScore=FRCW.misplaced();
		if(FRCWScore<min){
			min=FRCWScore;
			move=8;
		}
		//Front Rotate Counter Clockwise
		RubiksCube FRCCW=rc.clone();
		FRCCW.rotateLeft(Rotation.COUNTER);
		int FRCCWScore=FRCCW.misplaced();
		if(FRCCWScore<min){
			min=FRCCWScore;
			move=9;
		}
		//Back Rotate Clockwise
		RubiksCube BackRCW=rc.clone();
		BackRCW.rotateLeft(Rotation.COUNTER);
		int BackRCWScore=BackRCW.misplaced();
		if(BackRCWScore<min){
			min=BackRCWScore;
			move=10;
		}
		//Back Rotate Counter Clockwise
		RubiksCube BackRCCW=rc.clone();
		BackRCCW.rotateLeft(Rotation.COUNTER);
		int BackRCCWScore=BackRCCW.misplaced();
		if(BackRCCWScore<min){
			min=BackRCCWScore;
			move=11;
		}
		switch(move){
			case 0:rc.rotateLeft(Rotation.CLOCK);break;
			case 1:rc.rotateLeft(Rotation.COUNTER);break;
			case 2:rc.rotateRight(Rotation.CLOCK);break;
			case 3:rc.rotateRight(Rotation.COUNTER);break;
			case 4:rc.rotateTop(Rotation.CLOCK);break;
			case 5:rc.rotateTop(Rotation.COUNTER);break;
			case 6:rc.rotateBottom(Rotation.CLOCK);break;
			case 7:rc.rotateBottom(Rotation.COUNTER);break;
			case 8:rc.rotateFront(Rotation.CLOCK);break;
			case 9:rc.rotateFront(Rotation.COUNTER);break;
			case 10:rc.rotateBack(Rotation.CLOCK);break;
			case 11:rc.rotateBack(Rotation.COUNTER);break;
		}
		System.out.println("Going with move "+move);
		System.out.println(rc.toString());
	}
}
