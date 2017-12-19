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

    private static final float IMPULSE_SPEED = 50f;

    /**
     * Update class Variables
     */
    private float bx = 0, by = 0, bz = 10;
    private float Vz = 0;
    private float rad = 5;
    private float refl = 0.8f;
    private float constAcc = 1.08f; //ball falls down
    private int score = 0;
    private boolean jumping=true;

    /**
     * (0,0,0) updating the camera here to stay with the monster and not zero always
     */
    private float centerX = 0;
    private float centerY = 0;
    private float centerZ = 0;

     /**
     * The Monster will need to keep track of its own location which the main will update.
     */
    public Monster() {

    }

    /**
     * Hervé week07, use objects instance variable to access the application's
     * instance methods and variables
     */
    public void draw(PApplet app) {

        app.pushMatrix();

        app.translate(bx, by, bz);
        app.noStroke();
        app.fill(255, 0, 255);
        app.sphere(rad);

        app.popMatrix();
    }

    /**
     * update dt, time in seconds, since the last update.
     * Hervé -"we'll use the object's instance variable to access the
     * applications instance methods and variables."
     * brickZ is suppose to be location Z
     * brick width is the width of brick to see if monster is
     * actually ontop of brick at Z
     */
    public boolean update(float dt, Brick brick) {

        boolean landed = false;

        float brickZ = brick.getbz(),  brickX = brick.getbx(),  brickY = brick.getby();
        float bhw = brick.getWidth()/2, bhh = brick.getHeight()/2, bhd = brick.getDepth()/2;

        //the ball should jump here
        if(jumping){
            bz += Vz*dt - 8f*G*dt*dt;
            Vz -= G*dt;
             System.out.println("The balls' velocity is: " + Vz);
        }

        //Vz -= G * constAcc;
        //ball is currently on the brick
        if (    bx >= brickX - bhw && bx <= brickX + bhw &&
                by >= brickY - bhh && by <= brickY + bhh &&
                bz <= brickZ + bhd + rad) {

            //bz += brickZ;
            jumping = false;
            landed = true;
            bz = brickZ + bhd + rad;
            brick.isTouching(true);
            score++; //increment score, you landed on a brick
            //System.out.println("if: "+jump);
            println("STOP");

            //System.out.println("inside if statement");
            //velocity for the z plain multiplied by the velocity for the z
            //Vz = refl * PApplet.abs(Vz);

            //bz = brickZ + (bhh*2) + rad;

        }
        return landed;
    }


    public void jump() {
        if (!jumping) {
            jumping = true;
            Vz = IMPULSE_SPEED;
        }
    }
    /**
     * getScore returns the number
     * of bricks successfully jumped onto.
     */
    public float getScore(){
        return score;
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
    public boolean update(float dt) {
        return false;
    }
}
