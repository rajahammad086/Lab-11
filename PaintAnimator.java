import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class, designed to be used in conjunction with Paint, makes it possible
 * to create animated drawings.
 * 
 * @author Stina Bridgeman
 */

public class PaintAnimator extends TimerTask {

	/**
	 * The objects to be animated at each time step.
	 */

	private List<Object> toanimate_ = new ArrayList<Object>();

	/**
	 * The timer that invokes the animation.
	 */

	private Timer timer_;

	/**
	 * Create a new animator which will perform one animation step every 'delay'
	 * milliseconds.
	 * 
	 * @param delay
	 *          number of ms between animation steps
	 */

	public PaintAnimator ( long delay ) {
		timer_ = new Timer();
		timer_.schedule(this,delay,delay);
	}

	/**
	 * Start animating an object. The object must have a method of the form
	 * 
	 * <pre>
	 *   public void doAnimateStep () { ... }
	 * </pre>
	 * 
	 * doAnimateStep will be called for each step of the animation, and should
	 * handle updating whatever needs to be updated (e.g. changing the position)
	 * and drawing the new thing.
	 * 
	 * @param obj
	 *          object to animate
	 */

	public void animate ( Object obj ) {
		toanimate_.add(obj);
	}

	/**
	 * Stop animating an object. doAnimateStep() will no longer be called for the
	 * specified object.
	 * 
	 * @param obj
	 *          object to stop animating
	 */

	public void unanimate ( Object obj ) {
		toanimate_.remove(obj);
	}

	/**
	 * Carry out one animation step for each thing being animated. This method is
	 * used by the Java system, and should not be called directly.
	 */
	@Override
	public void run () {
		// clear the window
		if ( !toanimate_.isEmpty() ) { Paint.clear(); }

		// animate the objects
		for ( Object obj : toanimate_ ) {
			try {
				Method method =
				    obj.getClass().getMethod("doAnimateStep",(Class[]) null);
				method.invoke(obj,(Object[]) null);

			} catch ( SecurityException e ) {
				System.out.println("something went wrong with animating object " + obj);
				e.printStackTrace();
			} catch ( NoSuchMethodException e ) {
				System.out.println("cannot animate object " + obj
				    + " because it does not have a doAnimateStep() method");
				e.printStackTrace();
			} catch ( IllegalArgumentException e ) {
				System.out.println("something went wrong with animating object " + obj);
				e.printStackTrace();
			} catch ( IllegalAccessException e ) {
				System.out.println("something went wrong with animating object " + obj);
				e.printStackTrace();
			} catch ( InvocationTargetException e ) {
				System.out.println("something went wrong with animating object " + obj);
				e.printStackTrace();
			}

		}
	}

}