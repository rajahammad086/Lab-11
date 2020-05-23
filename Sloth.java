//Raja Hammad Mehmood
//Making a class for Sloth
import java.awt.Color;
public class Sloth extends Animal {
    protected int counter_; //sleep counter for sloth
    /**
     * constructor for Sloth with row and column and a random value for counter
     *  @param1 is the row.
     *  @param2 is the column.
     */
    public Sloth ( int row, int column) {
        super(row, column);
        counter_=(int)(Math.random()*21);
    }

    /**
     * constructor for rabbit with row, column assigned a value of -1 and a random value for counter
     */
    public Sloth ( ) {
        super();
        counter_=(int)(Math.random()*21);
    }

    /**
    * Returns the color of Sloth
    * @return is the color
    */
    public Color getColor() {
        return Color.GRAY;
    }

    /**
     * method for painting Sloth
     * @param1 is the x coordinate.
     *  @param2 is the y coordinate.
     *  @param3 is the width
     *  @param4 is the height
     */
    public void paint (int x, int y, int width, int height) {
        super.paint(x,  y,  width,  height);
        Paint.setColor(Color.BLACK);
        Paint.drawString("Zz",x+ width/3,y+height/2);
    }

    /**
     *  method to reset the memory of Sloth
     */
    public void reset() {
        counter_=(int)(Math.random()*21);
    }
    /**
     * method for getting the next position for Sloth
     *  @param1 is the object of type Field
     *  @return is the direction
     */
    public int getNextMove(Field object) {
        //sloth is fully awake (i.e. the sleep counter is 0), the sleep counter is set back to 20 and
        //a random direction is returned. Otherwise the sleep counter is decremented and Direction.NONE is returned
        if (counter_==0) {
            counter_=20;
            return Direction.random();
        } else {
            counter_=counter_ -1;
            return Direction.NONE;
        }
    }


}