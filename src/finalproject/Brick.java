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
    private float bx = 50, by = 0, bz = 30;
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

//        float monZ = monster.getX(),  monX = monster.getX(),  monY = monster.getZ();
//        float bhw = getWidth()/2, bhh = getHeight()/2, bhd = getDepth()/2;
//        float monRad = monster.getR();
        if (!isTrue) {
        bx -= .03f;//brick moves
//        if (    bx >= monX - bhw && bx <= monX + bhw &&
//                by >= monY - bhh && by <= monY + bhh &&
//                bz <= monZ + bhd + monRad) {
//
//            brickTouched = true;
//
//            System.out.println("brick should stop");
//            bx = 0;//brick stops
//
       }
//        System.out.print(" |isTrue "+isTrue+"|");
        else{
            //System.out.print("isTrue"+isTrue);
      //      bx -= 0;
        }



    }


    /**
     * We need to have a method that detects when the brick is touched
     * by the monster. If the monster lands on top we should stop the brick
     * and create a new brick at an updated y value so it aligns with top of
     * previous brick and game continues. If the brick hits the monster from the side,
     * the game should stop and restart. This task will be handled by isOnTop()
     *
     * @param tRad is the translated Radius of the ball
     * @return returns true if monster is inside the brick
     */
    public boolean isOnTop(float tRad) {
        //check w       check h        check depth
        return ((tRad >= bw) && (tRad >= bh) && (tRad >= bd));

    }

    /**
     *If the monster touches the brick, we want the brick to stop
     * animating, and the ball to stay on the brick.
     */
    public void isTouching(boolean isTrue) {


        this.isTrue =  isTrue;

    }

    /**
     * returns current depth
     */
    public float getDepth() {
        return bd;
    }

    /**
     * returns current width
     */
    public float getWidth() {
        return bw;
    }

    /**
     * returns current height
     */
    public float getHeight() {
        return bh;
    }


    /**
     * returns current  x translate
     */
    public float getbx() {
        return bx;
    }

    /**
     * returns current  y translate
     */
    public float getby() {
        return by;
    }

    /**
     * returns current  y translate
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
