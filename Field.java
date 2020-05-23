import java.awt.Color;

/**
 * A field, which contains things and roaming critters. The field is represented
 * as a grid, with each thing occupying exactly one grid cell and each grid cell
 * occupied by at most one thing at a time.
 */

public abstract class Field {

	private int numrows_, numcols_; // dimensions of the field (rows and columns)

	private int cellsize_; // dimensions of each grid cell, in pixels

	// the things in the field; field_[row][col] is null if the grid cell is empty
	private Thing[][] field_;

	private int step_; // the number of animation steps taken

	/**
	 * Create a new field with the specified dimensions.
	 * 
	 * @param numrows
	 *          number of rows (0 < numrows)
	 * @param numcols
	 *          number of columns (0 < numcols)
	 * @param cellsize
	 *          dimension of each grid cell, in pixels (0 < cellsize)
	 */

	public Field ( int numrows, int numcols, int cellsize ) {
		numrows_ = numrows;
		numcols_ = numcols;
		cellsize_ = cellsize;
		step_ = 0;
		field_ = new Thing[numrows][numcols];
		clearField();
	}

	/**
	 * Advance the simulation one step. (Does not include repainting.)
	 */

	public void advanceSimulation () {
		step_++;
	}

	/**
	 * Return the thing at the specified row and column in the field. Returns null
	 * if the spot is empty.
	 * 
	 * @param row
	 *          row of thing to return (0 <= row < height())
	 * @param col
	 *          column of thing to return (0 <= col < width())
	 */

	public Thing at ( int row, int col ) {
		return field_[row][col];
	}

	/**
	 * Determine if the specified animal can move one spot in the specified
	 * direction. Returns true if so, false if the animal is blocked by another
	 * thing or would move outside the bounds of the field.
	 * 
	 * @param animal
	 *          the animal whose movement is to be checked (animal != null and the
	 *          animal's position is in the field)
	 * @param direction
	 *          the direction to check (Direction.MIN_DIRECTION <= direction <=
	 *          Direction.MAX_DIRECTION or direction == Direction.NONE)
	 * @return true if the animal can move in the specified direction, false
	 *         otherwise
	 */

	public boolean canMove ( Animal animal, int direction ) {
		int row = animal.getRow() + rowChange(direction);
		int col = animal.getColumn() + colChange(direction);

		return (0 <= row && 0 <= col && row < numrows_ && col < numcols_ && field_[row][col] == null);
	}

	/**
	 * Clear the field so that it is empty except for the hedge of bushes around
	 * the outside edge.
	 */

	private void clearField () {
		/* clear the field */
		for ( int row = 0 ; row < getNumRows() ; row++ ) {
			for ( int col = 0 ; col < getNumCols() ; col++ ) {
				if ( row == 0 || col == 0 || row == getNumRows() - 1
				    || col == getNumCols() - 1 ) {
					field_[row][col] = new Bush();
				} else {
					field_[row][col] = null;
				}
			}
		}
	}

	/**
	 * Determine the change in column number if moving in the specified direction.
	 * 
	 * @param direction
	 *          the direction being moved
	 * @return the change in column number resulting from the specified direction
	 *         (-1 <= returned value <= 1)
	 */

	private int colChange ( int direction ) {
		switch ( direction ) {

		case Direction.WEST:
		case Direction.SW:
		case Direction.NW:
			return -1;

		case Direction.EAST:
		case Direction.SE:
		case Direction.NE:
			return 1;

		default:
			return 0;
		}
	}

	/**
	 * Determine the distance between the specified animal and the next thing,
	 * looking in the specified direction. The distance is 0 if direction is
	 * Direction.NONE. Returns the distance to the edge of the field if nothing
	 * blocks the animal's view.
	 * 
	 * @param animal
	 *          the animal who is looking (animal != null and the animal's
	 *          position is in the field)
	 * @param direction
	 *          the direction to look in (Direction.MIN_DIRECTION <= direction <=
	 *          Direction.MAX_DIRECTION or direction == Direction.NONE)
	 * @return the distance between the specified animal and the next thing in the
	 *         specified direction, or 0 if the direction is Direction.NONE
	 */

	public int distance ( Animal animal, int direction ) {

		if ( direction == Direction.NONE ) {
			return 0;
		}

		int drow = rowChange(direction), dcol = colChange(direction);

		int distance = 1;
		try {

			for ( int row = animal.getRow() + drow, col = animal.getColumn() + dcol ; at(
			                                                                             row,
			                                                                             col) == null ; row +=
			    drow, col += dcol, distance++ ) {}

		} catch ( ArrayIndexOutOfBoundsException e ) {}

		return distance;
	}

	/**
	 * Do one step of the animation, which means to advance the simulation one
	 * step and then repaint everything.
	 */

	public void doAnimateStep () {
		advanceSimulation();
		paint();
	}

	/**
	 * Returns the number of columns in the field.
	 */

	public int getNumCols () {
		return numcols_;
	}

	/**
	 * Returns the number of rows in the field.
	 */

	public int getNumRows () {
		return numrows_;
	}

	/**
	 * Return the current animation step number.
	 * 
	 * @return current animation step number
	 */

	public int getStep () {
		return step_;
	}

	/**
	 * Return the thing seen by the specified animal, looking in the specified
	 * direction. Returns null if the direction is Direction.NONE or nothing is
	 * visible before the edge of the field is reached.
	 * 
	 * @param animal
	 *          the animal whose is looking (animal != null and the animal's
	 *          position is in the field)
	 * @param direction
	 *          the direction to look in (Direction.MIN_DIRECTION <= direction <=
	 *          Direction.MAX_DIRECTION or direction == Direction.NONE)
	 * @return the closest thing to the specified animal in the specified
	 *         direction, or null if the direction is Direction.NONE or nothing is
	 *         visible in the specified direction
	 */

	public Thing look ( Animal animal, int direction ) {

		if ( direction == Direction.NONE ) {
			return null;
		}

		int drow = rowChange(direction), dcol = colChange(direction);

		try {

			int row, col;
			for ( row = animal.getRow() + drow, col = animal.getColumn() + dcol ; at(
			                                                                         row,
			                                                                         col) == null ; row +=
			    drow, col += dcol ) {}

			return at(row,col);

		} catch ( ArrayIndexOutOfBoundsException e ) {
			return null;
		}
	}

	/**
	 * Move the specified animal one space in the specified direction. If there is
	 * something already in that cell, it is overwritten.
	 * 
	 * @param animal
	 *          the animal to move (animal != null and the animal's position is in
	 *          the field)
	 * @param direction
	 *          the direction to move (canMove(animal,direction) is true, or it is
	 *          desired to overwrite the thing in the neighboring cell and the
	 *          neighboring cell is within the confines of the field)
	 */

	protected void move ( Animal animal, int direction ) {

		int row = animal.getRow() + rowChange(direction);
		int col = animal.getColumn() + colChange(direction);

		field_[animal.getRow()][animal.getColumn()] = null;
		field_[row][col] = animal;
		animal.setPosition(row,col);
	}

	/**
	 * Return the thing in the cell which is a neighbor of the specified animal
	 * and is in the specified direction. Returns null if the direction is
	 * Direction.NONE, the cell is empty, or the cell is outside the bounds of the
	 * field.
	 * 
	 * @param animal
	 *          the animal whose neighbor is being found (animal != null and the
	 *          animal's position is in the field)
	 * @param direction
	 *          the direction to look in (Direction.MIN_DIRECTION <= direction <=
	 *          Direction.MAX_DIRECTION or direction == Direction.NONE and the
	 *          animal is in the field)
	 * @return the thing in the adjacent cell in the specified direction, or null
	 *         if the direction is Direction.NONE, the neighboring cell is empty,
	 *         or the animal is not in the field
	 */

	public Thing neighbor ( Animal animal, int direction ) {

		if ( direction == Direction.NONE ) {
			return null;
		}

		int drow = rowChange(direction), dcol = colChange(direction);
		try {

			return at(animal.getRow() + drow,animal.getColumn() + dcol);

		} catch ( ArrayIndexOutOfBoundsException e ) {
			return null;
		}
	}

	/**
	 * Draw the field, including everything in it.
	 */

	public void paint () {
		Paint.clear();
		
		Paint.setColor(Color.WHITE);
		Paint.fillRect(0,0,numcols_ * cellsize_,numrows_ * cellsize_);
		Paint.setColor(Color.GRAY);
		Paint.drawRect(0,0,numcols_ * cellsize_ - 1,numrows_ * cellsize_ - 1);

		for ( int row = 0 ; row < numrows_ ; row++ ) {
			for ( int col = 0 ; col < numcols_ ; col++ ) {
				int x = col * cellsize_;
				int y = row * cellsize_;

				Thing thing = at(row,col);
				if ( thing != null ) {
					thing.paint(x,y,cellsize_,cellsize_);
				}
				Paint.setColor(Color.GRAY);
				Paint.drawRect(x,y,cellsize_,cellsize_);
			}
		}

	}

	/**
	 * Place the specified thing at a random open spot in the field. For animals,
	 * the animal's position is updated to reflect where the animal was placed.
	 * 
	 * @param thing
	 *          the thing to place
	 */

	protected void placeRandomly ( Thing thing ) {
		for ( ; true ; ) {
			int row = (int) (Math.random() * numrows_);
			int col = (int) (Math.random() * numcols_);
			if ( field_[row][col] == null ) {
				field_[row][col] = thing;
				if ( thing instanceof Animal ) {
					((Animal) thing).setPosition(row,col);
				}
				break;
			}
		}
	}

	/**
	 * Reset the field: it will be empty (except for a hedge of bushes completely
	 * surrounding the field) and any other simulation-related state will be reset
	 * to the initial values. The field is not repainted.
	 */

	public void reset () {
		clearField();
		step_ = 0;
	}

	/**
	 * Determine the change in row number if moving in the specified direction.
	 * 
	 * @param direction
	 *          the direction being moved
	 * @return the change in row number resulting from the specified direction (-1
	 *         <= returned value <= 1)
	 */

	private int rowChange ( int direction ) {
		switch ( direction ) {

		case Direction.NORTH:
		case Direction.NE:
		case Direction.NW:
			return -1;

		case Direction.SOUTH:
		case Direction.SE:
		case Direction.SW:
			return 1;

		default:
			return 0;
		}
	}

}
