package cube;

import states.Color;
import states.Rotation;

public interface Cube {

	/**
	 * Gets the current state of the given face.
	 * @param face Color of face to be returned.
	 * @return Array of face in its current state.
	 */
	public Color[][] getFace(Color face);
	
	/**
	 * Rotates the cube's front face
	 * @param r Direction of rotation
	 */
	public void rotateFront(Rotation r);
	
	/**
	 * Rotates the cube's back face
	 * @param r Direction of rotation
	 */
	public void rotateBack(Rotation r);
	
	/**
	 * Rotates the cube's left face
	 * @param r Direction of rotation
	 */
	public void rotateLeft(Rotation r);
	
	/**
	 * Rotates the cube's right face
	 * @param r Direction of rotation
	 */
	public void rotateRight(Rotation r);
	
	/**
	 * Rotates the cube's top face
	 * @param r Direction of rotation
	 */
	public void rotateTop(Rotation r);
	
	/**
	 * Rotates the cube's bottom face
	 * @param r Direction of rotation
	 */
	public void rotateBottom(Rotation r);
	
	/**
	 * Determines if the cube is solved or not.
	 * @return State of cube solution.
	 */
	public boolean isSolved();
	
}
