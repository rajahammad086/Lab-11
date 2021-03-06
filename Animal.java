//Raja Hammad Mehmood
//Making an animal class
import java.awt.Color;
public abstract class Animal extends Thing {
    protected int row_; // row for the thing
    protected int column_;// column of the thing
    /**
     * constructor for animal
     *  @param1 is the row.
     *  @param2 is the column.
     */

    public Animal(int row, int column) {
        row_=row;
        column_=column;
    }

    /**
     * constructor for animal which sets row and column to -1
     */

    public Animal() {
        row_=-1;
        column_=-1;
    }
    /**
    * method for painting animal
    * @param1 is the x coordinate.
    *  @param2 is the y coordinate.
    *  @param3 is the width
    *  @param4 is the height
    */
    public void paint (int x, int y, int width, int height) {
        Paint.setColor(getColor());
        Paint.fillOval(x, y,width,height);
    }


    /**
     * Returns the row
     * @return is the row
     */

    public int getRow() {
        return row_;
    }

    /**
     * Returns the column
     * @return is the column
     */

    public int getColumn() {
        return column_;
    }

    /**
     * setter for animal's position
     *  @param1 is the row.
     *  @param2 is the column.
     */

    public void setPosition(int row, int column) {
        row_=row;
        column_=column;
    }

    /**
     * abstract method for getting the next position
     *  @param1 is the object of type Field
     *  @return is the direction
     */
    public abstract int getNextMove(Field object);

    /**
     * abstract method to reset the memory
     */
    public abstract void reset();



}