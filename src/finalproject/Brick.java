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


	//-----------------------------
	//	Various status variables
	//-----------------------------
	private float timeIndex = 0, xIndex = 1, yIndex = 2, aIndex = 3;

	/**
	 * Update class Variables
	 */
    private float bx = -20, by = 0, bz = 30;
    private float Vx = 12, Vy = 0, Vz = 0;
    private float rad = 5;
    private float refl = 0.8f;
    private static final float ZERO_SPEED = 0.01f;

    /**
     * private static PApplet app;
     * private static int appSetCounter = 0;
     * used in PApplet setup
     */
    private static PApplet app;
    private static int appSetCounter = 0;

    //-----------------------------
    //	graphical objects
    //-----------------------------
    private ArrayList<KeyFrame> keyFrames;


    /**
     * The constructor for brick needs to be passed a random velocity,
     * a predetermined keyframe path (right to left), x and y start value. Y will update
     * for each new object in the Main setup();
     */
    public Brick( ArrayList<KeyFrame> keyFramesPar){
    	keyFrames = keyFramesPar;
    	timeIndex = keyFrames.get(0).getTime();//the first keyFrames in our classes array list
    	                                       //of key frames that have the get methods
    	xIndex = keyFrames.get(0).getY();
    	yIndex = keyFrames.get(0).getX();  
    	aIndex = keyFrames.get(0).getAngle();
    }

    /**
     * Hervé week07, use objects instance variable to access the application's
     * instance methods and variables
     */
    public void draw(PApplet app_){
        app_.pushMatrix();

        app_.translate(bx, by, bz);
        app_.color(0,250,0);
        app_.noStroke();
        app_.fill(0,0,0);
        app_.box(40,25,20);
        app_.popMatrix();
    }

    /**
     * update dt, time in seconds, since the last update.
     * Hervé - we'll use the object's instance variable to access the
     * applications instance methods and variables.
     */
    public void update(float dt){

        if (bz <= rad) {
            Vz = refl * PApplet.abs(Vz);
            Vx *= refl;
            Vy *= refl;

            if (PApplet.abs(Vx) < ZERO_SPEED)
                Vx = 0.f;
            if (PApplet.abs(Vy) < ZERO_SPEED)
                Vy = 0.f;
            if (PApplet.abs(Vz) < ZERO_SPEED)
                Vz = 0.f;

        }
        float halfdt2 = 0.5f * dt*dt;
        
        bx += Vx * dt;
        by += Vy * dt;
        bz += Vz * dt - G*halfdt2;

        Vz -= G * dt;

    }


    /**
     * We need to have a method that detects when the brick is touched
     * by the monster. If the monster lands on top we should stop the brick
     * and create a new brick at an updated y value so it aligns with top of
     * previous brick and game continues. If the brick hits the monster from the side,
     * the game should stop and restart. This task will be handled by isInside()
     * @param thY
     * @param theX
     * @return
     */
    public boolean isInside(float thY, float theX){
        return false;
    }

    /**
     * We use the static counter
     * to let the variable be set only once.
     */	
    protected static int setup(PApplet theApp)
    {
        if (appSetCounter == 0)
        {
            app = theApp;
            appSetCounter = 1;
        }
        else
            appSetCounter = 2;

        return appSetCounter;

    }
}
