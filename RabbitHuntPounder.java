/*
 * Run the rabbit chase many times, and print out the percentage of times the
 * rabbit successfully evades the fox for 100 steps.
 */

public class RabbitHuntPounder {

	public static final int NUM_ITER = 100000; // number of repetitions

	private static final int NUMROWS = 20, NUMCOLS = 20;

	private static final int CELLSIZE = 25;

	public static void main ( String[] args ) {

		// the field
		RabbitHuntField field = new RabbitHuntField(NUMROWS,NUMCOLS,CELLSIZE);

		int evasions = 0; // # of successful evasions so far

		for ( int ctr = 0 ; ctr < NUM_ITER ; ctr++ ) {
			for ( field.reset() ; field.isRabbitFree() && field.getStep() < 100 ; field
			    .advanceSimulation() ) {}

			if ( ctr % 1000 == 0 ) {
				System.out.print(".");
			}

			if ( field.isRabbitFree() ) {
				evasions++;
			}
		}

		System.out.println();
		System.out.println("evasions: " + ((evasions * 100.0) / NUM_ITER) + "%");
	}

}
