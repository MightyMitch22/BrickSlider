package finalproject;

/**
 * The brick is going to be a rectangle that slides from right to left
 * When the monster lands on the block, the block stops updating, and as a result
 * stops at the point it is at.
 * Created by Mitch on 11/29/17.
 */
public class Brick extends GraphicObject implements ApplicationConstants, AnimatedObject {

    /**
     * The constructor for brick needs to be passed a random velocity,
     * a predetermined keyframe path (right to left), x and y start value. Y will update
     * for each new object in the Main setup();
     */
    public Brick(){

    }

    /**
     * Hervé week07, use objects instance variable to access the application's
     * instance methods and variables
     */
    public void draw(){
        app_.pushMatrix();

        app_.popMatrix();
    }

    /**
     * update dt, time in seconds, since the last update.
     * Hervé - we'll use the object's instance variable to access the
     * applications instance methods and variables.
     */
    public void update(float dt){



    }

    /**
     * We need to have a method that detects when the brick is touched
     * by the monster. If the monster lands on top we should stop the brick
     * and create and game continues. If the brick hits the monster from the side,
     * the game should stop. This task will be handled by isInside()
     * @param thY
     * @param theX
     * @return
     */
    public boolean isInside(float thY, float theX){
        return false;
    }
}
