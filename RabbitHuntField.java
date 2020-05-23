//Raja Hammad Mehmood                                           //where instanceOf    fox bushes  how to check evasion
//
import java.awt.Color;
public class RabbitHuntField extends Field {
    protected boolean free_;// keeps track of whether whether whether or not the rabbit is free.
    protected Animal[] animal_=new Animal[17];// has all the animals in one array
    

    /**
     * constructor for RabbitHuntField
     *  @param1 is dimensions of the field (rows )
     *  @param2 dimensions of the field (columns)
     *  @param3 is dimensions of each grid cell, in pixels
     */
    public RabbitHuntField (int numrows, int numcols, int cellsize) {
        super(numrows, numcols,cellsize);
        free_=true;
        animal_[0]= new Rabbit();
        animal_[1]=new Fox();
        for(int i=2; i<animal_.length;i++){
            Sloth sloth=new Sloth();
        animal_[i]=sloth;
        }
        for(int i=0; i<animal_.length;i++){
        placeRandomly(animal_[i]);
        }
        for(int i=0; i<20;i++){
            Bush bush=new Bush();
        placeRandomly(bush);
        }
    }

    /**
    * a getter isRabbitFree which returns whether or not the rabbit is free
    * @return is the true or false
    */
    public boolean isRabbitFree() {
        return free_;
    }

    
    /**
    * a getter isRabbitFree which returns whether or not the rabbit is free
    * @return is the true or false
    */
    public void reset() {
        super.reset();
        for(int i=0; i<animal_.length;i++){
        placeRandomly(animal_[i]);
        }
        free_=true;
        for(int i=0; i<animal_.length;i++){
        placeRandomly(animal_[i]);
        }
        for(int i=0; i<20;i++){
            Bush bush=new Bush();
        placeRandomly(bush);
        }
    }
    
    
    /**
    * a getter isRabbitFree which returns whether or not the rabbit is free
    * @return is the true or false
    */
    public void advanceSimulation() {
        if(free_=true){
        super.advanceSimulation();
        for(int i=0; i<animal_.length;i++){
        int direction= animal_[i].getNextMove(this);
        if(animal_[i] instanceof Fox){
            free_=false;
             move(animal_[i],direction);
        }
        else{
            if(canMove(animal_[i],direction)==true){
                move(animal_[i],direction);   
            }
        }
        }
        }
        
    }
    //if the simulation is over (i.e. the rabbit has been caught or the rabbit has eluded the fox for 100 steps),
   // print a message identifying the winner (fox or rabbit) and then reset and repaint the field. If not, call Field's doAnimateStep.
    /**
    * a getter isRabbitFree which returns whether or not the rabbit is free
    * @return is the true or false
    */
    public void doAnimateStep() {
        if(free_==false){
            System.out.println("Fox Wins!");
        }
        else{
            super.doAnimateStep();
        }
        
    }
    
    
    
    
    
    
  


}