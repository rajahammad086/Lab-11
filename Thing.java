//Raja Hammad Mehmood
//Making a thing class
import java.awt.Color;
public abstract class Thing {



    /**
     * abstract method for getting color
     */

    public abstract Color getColor ();

    /**
     * abstract method for painting something
     * @param1 is the x coordinate.
     *  @param2 is the y coordinate.
     *  @param3 is the width
     *  @param4 is the height
     */
    public abstract void paint (int x, int y, int width, int height);





}