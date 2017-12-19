package finalproject;

import processing.core.PApplet;

/**
 * The brick is going to be a rectangle that slides from right to left
 * When the monster lands on the block, the block stops updating, and as a result
 * stops at the point it is at.
 * Created by Mitch on 11/29/17.
 */
public class Brick extends PApplet implements ApplicationConstants, AnimatedObject {


    //-----------------------------
    //	Various status variables
    //-----------------------------
    /**
     * Update class Variables
     */
    //translation of brick
    private float bx = 0, by = 0, bz = 0;
    //actual brick
    private float bw = 40, bh = 25, bd = 6;
    //private float Vx = 12, Vy = 0, Vz = 0;


    /**
     * private static PApplet app;
     * private static int appSetCounter = 0;
     * used in PApplet setup
     */
    private static PApplet app;
    private static int appSetCounter = 0;

    private boolean isTrue = false;


    /**
     * The constructor for brick needs to be passed a random velocity,
     * a predetermined keyframe path (right to left), x and y start value. Y will update
     * for each new object in the Main setup();
     */
    public Brick() {

    }

    public Brick(float x, float y, float z) {
        bx = x;
        by = y;
        bz = z;
    }

    /**
     * Hervé week07, use objects instance variable to access the application's
     * instance methods and variables
     */
    public void draw(PApplet app_) {
        app_.pushMatrix();

        app_.translate(bx, by, bz);
        app_.noStroke();
        app_.fill(0, 0, 255);
        app_.box(bw, bh, bd);

        app_.popMatrix();
    }

    /**
     * update dt, time in seconds, since the last update.
     * Hervé - "we'll use the object's instance variable to access the
     * applications instance methods and variables."
     * <p>
     * what we have now:
     * When v is pressed the animate becomes true and update occurs.
     * ball bounces according to gravity up and down
     * <p>
     * what we want:
     * when v is pressed we want ball to bounce up and then fall down
     * onto the brick
     * when pressed again we want it to bounce back up and then fall
     * onto the brick
     */
    public void update(float dt) {

        //-----------------------------
        // moves brick right to left
        // when animate is true in main
        // brick stops moving when center of
        // monster touches top of brick
        //-----------------------------

        if (!isTrue) {
        bx -= .03f;//brick moves
       }
        else{
            //don't do anything
        }
    }

    /**
     *  If the ball touches the brick, isTouching is
     *  called from monster and changes the boolean isTrue
     *  to true or false
     */
    public void isTouching(boolean isTrue) {

        this.isTrue =  isTrue;

    }

    /**
     * returns current isTouching status
     */
    public boolean getIsTouching(){
        return isTrue;
    }

    /**
     * returns brick width
     */
    public float getWidth() {
        return bw;
    }

    /**
     * returns brick height
     */
    public float getHeight() {
        return bh;
    }

    /**
     * returns brick depth
     */
    public float getDepth() {
        return bd;
    }

    /**
     * returns current x translate
     */
    public float getbx() {
        return bx;
    }

    /**
     * returns current y translate
     */
    public float getby() {
        return by;
    }

    /**
     * returns current z translate
     */
    public float getbz() {
        return bz;
    }

    /**
     * We use the static counter
     * to let the variable be set only once.
     */
    protected static int setup(PApplet theApp) {
        if (appSetCounter == 0) {
            app = theApp;
            appSetCounter = 1;
        } else
            appSetCounter = 2;

        return appSetCounter;

    }
}
