package states;

public enum Color {
	WHITE, YELLOW, BLUE, RED, ORANGE, GREEN;
	
	public Color[][] face(){
		Color[][]face = new Color[3][3];
		for(int x = 0; x < 3; x++){
			for(int y = 0; y < 3; y++){
				face[x][y] = this;
			}
		}
		return face;
	}
	
	public String code(){
		return this.toString().substring(0, 3);
	}
}
