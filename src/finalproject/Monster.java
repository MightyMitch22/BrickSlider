package finalproject;

/**
 * The Monster jumps, dies, updates animation.
 * Physics Image processing will be used to move the monster.
 * When the Monster hits the block, the block stops. If the
 * block was hit on the side the monster dies, if the monster lands
 * on top the
 * Created by Mitch on 11/29/17.
 */
public class Monster extends GraphicObject implements ApplicationConstants, AnimatedObject {

    /**
     * The Monster will need to keep track of its own location which the main will update.
     *
     */
    public Monster(){

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
     * We need to have a method that detects when the Monster is touched
     * by the brick. If the monster lands on top we should stop the brick
     * and create and game continues. If the brick hits the monster from the side,
     * the game should stop.
     * @param thY
     * @param theX
     * @return
     * (Do we need this method in Monster or Brick, or both)
     */
    public boolean isInside(float thY, float theX){
        return false;
    }

}
