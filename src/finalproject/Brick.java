package finalproject;

import processing.core.PApplet;

/**
 * The brick is going to be a rectangle that slides from right to left
 * When the monster lands on the block, the block stops updating, and as a result
 * stops at the point it is at.
 * Created by Mitch on 11/29/17.
 */
public class Brick extends PApplet implements ApplicationConstants, AnimatedObject {

    /**
     * private static PApplet app;
     * private static int appSetCounter = 0;
     * used in PApplet setup
     */
    public static final float bw = 40, bh = 25, bd = 6;

    private static final float BASE_SPEED = 20;
    private static final float SPEED_INCR = 1.1f;
    private static int brickCounter = 0;
    private static float speedFactor = 1.f;

    //-----------------------------
    //	Various status variables
    //-----------------------------
    /**
     * Update class Variables
     */
    //translation of brick
    private float bx, by, bz;
    private float Vx;


    /**
     * The constructor for brick needs to be passed a random velocity,
     * a predetermined keyframe path (right to left), x and y start value. Y will update
     * for each new object in the Main setup();
     */
    public Brick(int n) {

        //  create the brick outside of the window, at the proper level (based on n)
        if (n != 0)
            bx = XMAX + (6 + (int)(5*Math.random())*bw);
        else
            bx = 10;// hard code the first brick
        by = 0;
        bz = (n)*bd;

        Vx = speedFactor*BASE_SPEED;

        brickCounter++;
        speedFactor *= SPEED_INCR;
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
    public boolean update(float dt) {

        //-----------------------------
        // moves brick right to left
        // when animate is true in main
        // brick stops moving when center of
        // monster touches top of brick
        //-----------------------------

        boolean brickOut = false;
        bx -= Vx*dt;//brick moves
            if (bx < XMIN*1.1f)
                brickOut = true;

        return brickOut;
    }

    /**
     *  If the ball touches the brick, isTouching is
     *  called from monster and changes the boolean isTrue
     *  to true or false
     */
    public void isTouching() {

        Vx = 0;

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

  }
