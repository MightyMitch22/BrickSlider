package finalproject;

import processing.core.PApplet;

/**
 * The Monster class checks to see if the ball (monster) is touching the brick.
 * If the ball detects the brick, it changes landed to true which then causes the brick
 * to stop moving, and a new brick to be drawn and animated.
 */
public class Monster extends PApplet implements ApplicationConstants, AnimatedObject {

    //-----------------------------
    //	Various status variables
    //-----------------------------

    private static final float IMPULSE_SPEED = 50f;
    private static final float BUMP_SPEED = 50f;

    /**
     * Update class Variables
     */
    private float bx = 0, by = 0, bz = Brick.bd * 8;
    private float Vz = 0;
    private float Vx = 0;
    private float rad = 5;


    private boolean jumping = true;


    /**
     * (0,0,0) updating the camera here to stay with the monster and
     * not zero always
     */
    private float centerX = 0;
    private float centerY = 0;
    private float centerZ = 0;

    /**
     * The Monster will need to keep track of its own location which
     * the main will update.
     */
    public Monster() {

    }

    /**
     * draw() draws the 3D sphere, which is the monster, then translates
     * the sphere to move it while the dimensions stay the same.
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
    public int update(float dt, Main app, Brick brick1, Brick brick2) {

        int status = 0;
        boolean landed = false;
        boolean gameOver = false;

        //when the ball is jumping, there is an impulse
        //but then this math creates a "jumping" action
        if (jumping) {
            bz += Vz * dt - 8f * G * dt * dt;
            Vz -= G * dt;

            // test against the surface
            if (bz < rad) {
                app.setGameOver();
                System.out.println("Game Over");
            }
        }

        if (!gameOver) {
            landed = testBrick(brick1);
            if (landed) { //if ball landed on brick
                status = 1;
            }
            if (!landed && brick2 != null) {
                landed = testBrick(brick2);
                if (landed) {//if brick landed on previously moving brick
                    status = 2;
                }
            }
        }
        //test if monster has not landed and is it hit by brick
        if (!landed && testBump(brick1)) {
            Vx = BUMP_SPEED;
            jumping = true;
            System.out.println("side bump");
        }

        return status;
    }

    /**
     * testBump is checking if the ball is hit by the side
     * of the brick
     *
     * @param theBrick the current brick moving towards
     *                 the monster
     * @return status of true if the ball detects side of
     * brick
     */
    private boolean testBump(Brick theBrick) {
        float brickZ = theBrick.getbz(), brickX = theBrick.getbx(), brickY = theBrick.getby();
        float bhw = theBrick.getWidth() / 2, bhh = theBrick.getHeight() / 2, bhd = theBrick.getDepth() / 2;


        boolean bump = true;
        //tests for ball hiting side of brick
        if (bx > brickX + bhw + rad &&
                bz > brickZ - bhd && bz <= brickZ + bhd) {

            println("Test Bump");
//            System.out.println("x = " + bx + "   z = " + bz);
//            System.out.println("brick x = " + brickX + "   z = " + brickZ);
//            System.out.println("" + brickX + " < " + bx + " < " + brickX + bhw + rad);
//            System.out.println("" + (brickZ - bhd) + " < " + bz + " < " + brickZ + bhd);


//                bx >= brickX - bhw && bx <= brickX + bhw + rad &&
//                by >= brickY - bhh && by <= brickY + bhh &&
//                bz >= brickZ - bhd && bz <= brickZ - bhd)
        }
        return bump;
    }

    /**
     *
     * @param theBrick the current brick moving towards
     *                 the monster
     * @return if the ball touches the brick, landed is true,
     * causes brick to stop moving
     */
    private boolean testBrick(Brick theBrick) {
        boolean landed = false;

        float brickZ = theBrick.getbz(), brickX = theBrick.getbx(), brickY = theBrick.getby();
        float bhw = theBrick.getWidth() / 2, bhh = theBrick.getHeight() / 2, bhd = theBrick.getDepth() / 2;

        //ball is currently on the brick
        if (bx >= brickX - bhw && bx <= brickX + bhw &&
                bz <= brickZ + bhd + rad) {

            jumping = false;
            landed = true;
            bz = brickZ + bhd + rad;
            theBrick.isTouching();

        }

        return landed;
    }

    /**
     * jump() is used when the key "J" is pressed. The ball is
     * given an impulse upwards and then gradually slows down
     * and falls onto the brick, or surface.
     */
    public void jump() {
        if (!jumping) {
            jumping = true;
            Vz = IMPULSE_SPEED;
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

    /**
     * intelli j automatically generated
     * @param dt
     * @return
     */
    @Override
    public boolean update(float dt) {
        return false;
    }
}
