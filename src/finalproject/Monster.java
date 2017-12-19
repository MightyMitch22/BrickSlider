package finalproject;

import processing.core.PApplet;

/**
 * The Monster jumps, dies, updates animation.
 * Physics Image processing will be used to move the monster.
 * When the Monster hits the block, the block stops. If the
 * block was hit on the side the monster dies, if the monster lands
 * on top the
 * Created by Mitch on 11/29/17.
 */
public class Monster extends PApplet implements ApplicationConstants, AnimatedObject {

    //-----------------------------
    //	Various status variables
    //-----------------------------

    /**
     * Update class Variables
     */
    private float bx = 0, by = 0, bz = 8;
    private float Vx = 12, Vy = 0, Vz = 0;
    private float rad = 5;
    private float refl = 0.8f;
    private float constAcc = .08f; //ball falls down
    private static final float ZERO_SPEED = 0.01f;

    /**
     * (0,0,0) updating the camera here to stay with the monster and not zero always
     */
    private float centerX = 0;
    private float centerY = 0;
    private float centerZ = 0;

    /**
     * private static PApplet app_;
     * private static int appSetCounter_ = 0;
     * used in PApplet setup
     */
    private static PApplet app;
    private static int appSetCounter = 0;

    /**
     * The Monster will need to keep track of its own location which the main will update.
     */
    public Monster() {

    }

    /**
     * Hervé week07, use objects instance variable to access the application's
     * instance methods and variables
     */
    public void draw(PApplet app_) {

        app_.pushMatrix();

        app_.translate(bx, by, bz);
        app_.noStroke();
        app_.fill(255, 0, 255);
        app_.sphere(rad);

        app_.popMatrix();
    }

    /**
     * update dt, time in seconds, since the last update.
     * Hervé -"we'll use the object's instance variable to access the
     * applications instance methods and variables."
     * brickZ is suppose to be location Z
     * brick width is the width of brick to see if monster is
     * actually ontop of brick at Z
     */
    public void update(Brick brick, boolean jump) {


        float brickZ = brick.getbz(),  brickX=brick.getbx(),  brickY=brick.getby();
        float bhw = brick.getWidth()/2, bhh = brick.getHeight()/2, bhd = brick.getDepth()/2;

        //Vz -= G * constAcc;
        if (    bx >= brickX - bhw && bx <= brickX + bhw &&
                by >= brickY - bhh && by <= brickY + bhh &&
                bz <= brickZ + bhd + rad && !jump) {

            bz += -2 * constAcc;

            brick.isTouching(true);
            //System.out.println("if: "+jump);

            //System.out.println("inside if statement");
            //velocity for the z plain multiplied by the velocity for the z
            //Vz = refl * PApplet.abs(Vz);

            bz = brickZ + bhd + rad;

            /// if statement that waits for ball fell
            //boolean that says ball falling vs ball fell


        }
        else if(jump){
            //System.out.println("else if: "+jump);
            jump = false;
        }
    }

    /**
     * returns current bx
     */
    public float getX() {
        return bx;
    }

    /**
     * returns current by
     */
    public float getY() {
        return by;
    }

    /**
     * returns current bz
     */
    public float getZ() {
        return bz;
    }

    /**
     * getR, gets the radius from the monster sphere
     */
    public float getR() {
        return rad;
    }


    /**
     * We need to have a method that detects when the Monster is touched
     * by the brick. If the monster lands on top we should stop the brick
     * game continues. If the brick hits the monster from the side,
     * the game should stop.
     *
     * @param thY y for isOnTop
     * @param theX x for isOnTop
     * @return (Do we need this method in Monster or Brick, or both)
     */
    public boolean isInside(float thY, float theX) {
        return false;
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
     * (camera)
     * returns current centerX
     */
    public float updateCameraX() {

        return centerX = getX();
    }

    /**
     * (camera)
     * returns current centerY
     */
    public float updateCameraY() {

        return centerY = getY();

    }

    /**
     * (camera)
     * returns current centerZ
     */
    public float updateCameraZ() {

        return centerZ = getZ();

    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub

    }


}
