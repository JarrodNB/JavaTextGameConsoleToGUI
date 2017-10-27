
package Models;

import java.io.Serializable;

public class RoomPuzzle extends RoomObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -902475575781932775L;
	private Puzzle puzzle;
	
	public RoomPuzzle(Puzzle puzzle) {
		super();
		this.puzzle = puzzle;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}
	
}
