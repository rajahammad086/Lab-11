//Raja Hammad Mehmood
//Making a class for the bush
import java.awt.Color;
public class Bush extends Thing {

    /**
    * Returns the color of the Bush
    * @return is the color
    */
    public Color getColor() {
        return Color.GREEN;
    }

    /**
     * method for painting the Bush
      * @param1 is the x coordinate.
     *  @param2 is the y coordinate.
     *  @param3 is the width
     *  @param4 is the height
     */
    public void paint (int x, int y, int width, int height) {
        Paint.setColor(Color.GREEN);
        Paint.fillRect(x, y,width,height);
    }





}