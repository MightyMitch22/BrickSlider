package finalproject;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * The brick is going to be a rectangle that slides from right to left
 * When the monster lands on the block, the block stops updating, and as a result
 * stops at the point it is at.
 * Created by Mitch on 11/29/17.
 */
public class Brick extends PApplet implements ApplicationConstants, AnimatedObject {


    private static final float ZERO_SPEED = 0.01f;
    /**
     * private static PApplet app;
     * private static int appSetCounter = 0;
     * used in PApplet setup
     */
    private static PApplet app;
    private static int appSetCounter = 0;
    //-----------------------------
    //	Various status variables
    //-----------------------------
    private float timeIndex = 0, xIndex = 1, yIndex = 2, aIndex = 3;
    /**
     * Update class Variables
     */
    private float bx = 50, by = 0, bz = 1;
    private float bw = 40, bh = 25, bd = 20;
    private float Vx = 12, Vy = 0, Vz = 0;
    private float rad = 5;
    private float refl = 0.8f;
    //-----------------------------
    //	graphical objects/I don't think I need this...
    //-----------------------------
    private ArrayList<KeyFrame> keyFrames;


    /**
     * The constructor for brick needs to be passed a random velocity,
     * a predetermined keyframe path (right to left), x and y start value. Y will update
     * for each new object in the Main setup();
     */
    public Brick() {

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
        System.out.println(bx);

        app_.popMatrix();
    }

    /**
     * update dt, time in seconds, since the last update.
     * Hervé - we'll use the object's instance variable to access the
     * applications instance methods and variables.
     * <p>
     * what we have now:
     * When v is pressed the animate becomes true and update occurs.
     * ball bounces according to gravity up and down
     * <p>
     * what we want:
     * when v is pressed we want ball to bounce up and then fall down
     * when pressed again we want it to bounce back up and then fall
     */
    public void update(float dt) {

        //--------------------------
        // moves brick right to left
        //--------------------------
        bx -= .08f;

    }

    /**
     * We need to have a method that detects when the brick is touched
     * by the monster. If the monster lands on top we should stop the brick
     * and create a new brick at an updated y value so it aligns with top of
     * previous brick and game continues. If the brick hits the monster from the side,
     * the game should stop and restart. This task will be handled by isInside()
     *
     * @param tRad
     * @return
     */
    public boolean isInside(float tRad) {
        //check w       check h        check depth
        return ((rad >= bw) && (rad >= bh) && (rad >= bd));
    }
}
