//Raja Hammad Mehmood
//Making a class for Rabbit
import java.awt.Color;
public class Rabbit extends Animal {
    /**
     * constructor for Rabbit
     *  @param1 is the row.
     *  @param2 is the column.
     */

    public Rabbit ( int row, int column ) {
        super(row, column);
    }

    /**
      * constructor for rabbit with row, column assigned a value of -1
      */

    public Rabbit ( ) {
        super();
    }
    /**
    * Returns the color of Rabbit
    * @return is the color
    */
    public Color getColor() {
        Color object= new Color(165,125,0);
        return object;
    }


    /**
        *rabbit has no memory so no need to reset anything
        */
    public void reset() {

    }
    /**
     * method for getting the next position for rabbit
     *  @param1 is the object of type Field
     *  @return is the direction
     */
    public int getNextMove(Field object) {
        return  Direction.random();
    }


}