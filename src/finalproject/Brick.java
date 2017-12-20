package finalproject;

import processing.core.PApplet;

/**
 * The brick is going to be a rectangle that slides from right to left
 * When the monster lands on the block, the block stops updating, and as a result
 * stops at the point it was at when the monster landed on it.
 */
public class Brick extends PApplet implements ApplicationConstants, AnimatedObject {

    //-----------------------------
    //	Various Static variables
    //-----------------------------
    public static final float bw = 40, bh = 25, bd = 6;

    private static final float BASE_SPEED = 20;
    private static final float SPEED_INCR = 1.1f;
    private static int brickCounter = 0; //Hervé Why did we make a counter static?
    private static float speedFactor = 1.f;

    //-----------------------------
    //	Update class variables
    //-----------------------------
    //translation of brick
    private float bx, by, bz;
    private float Vx;


    /**
     * The constructor for brick needs to be passed a number so the z on the
     * plain can be updated when the brick is draw so the continuation of bricks
     * being drawn appear on top of one an other.
     */
    public Brick(int n) {

        //  create the brick outside of the window, at the proper level (based on n)
        if (n != 0)
            bx = XMAX + (6 + (int) (5 * Math.random()) * bw);
        else
            bx = 10;// hard code the first brick
        by = 0;
        bz = (n) * bd;

        Vx = speedFactor * BASE_SPEED;

        brickCounter++;
        speedFactor *= SPEED_INCR;
    }

    /**
     * draw() draws a 3D rectangle and uses translate to move
     * the rectangle around without changing the dimensions
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
     * update is called from the main to update the location
     * of the brick.
     * @param dt the change in time between frames
     * @return brickOut which is when the brick exits
     */
    public boolean update(float dt) {

        boolean brickOut = false;
        bx -= Vx * dt;//brick moves
        if (bx < XMIN * 1.1f)
            brickOut = true;

        return brickOut;
    }

    /**
     * If the ball touches the brick, isTouching is
     * called from monster and changes the velocity x, Vx,
     * to a value of 0, so the brick stops moving along
     * the x axis.
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
     * returns current x translate of brick
     */
    public float getbx() {
        return bx;
    }

    /**
     * returns current y translate of brick
     */
    public float getby() {
        return by;
    }

    /**
     * returns current z translate of brick
     */
    public float getbz() {
        return bz;
    }

}
