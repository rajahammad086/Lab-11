import java.awt.Color;

/**
 * Main program for rabbit hunt. It allows the user to step the simulation one
 * step at a time or to let it run.
 * 
 * @author Stina Bridgeman
 */
public class RabbitHunt {

	private static final int DEFAULT_DELAY = 500; // animation speed, ms

	private static final int NUMROWS = 20, NUMCOLS = 20; // grid size

	private static final int CELLSIZE = 25; // size of grid cells, in pixels

	public static void main ( String[] args ) {
		// create the drawing window
		Paint.buildWindow("rabbit hunt",100,100,NUMCOLS * CELLSIZE,NUMROWS
		    * CELLSIZE,Color.WHITE);

		// set up the field
		RabbitHuntField field = new RabbitHuntField(NUMROWS,NUMCOLS,CELLSIZE);
		field.paint();
		
		// set up the animator
		PaintAnimator animator = new PaintAnimator(DEFAULT_DELAY);

		// handle user input
		while ( true ) {
			char ch = Paint.getChar();
			if ( ch == 'r' ) { // reset
				field.reset();
				field.paint();
			} else if ( ch == 'g' ) { // start the animation
				// first make sure we don't accidentally try to animate the field twice
				animator.unanimate(field);
				animator.animate(field);
			} else if ( ch == 'p' ) { // pause the animation
				animator.unanimate(field);
			} else if ( ch == 's' ) { // step one step of the animation
				animator.unanimate(field);
				field.advanceSimulation();
				field.paint();
			}
		}
	}

}
