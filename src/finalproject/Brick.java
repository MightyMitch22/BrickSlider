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


    private float bx_ = -20, by_ = 0, bz_ = 30;
    private float Vx_ = 12, Vy_ = 0, Vz_ = 0;
    private float rad_ = 5;
    private float refl_ = 0.8f;
    private static final float ZERO_SPEED = 0.01f;

    /**
     * private static PApplet app_;
     * private static int appSetCounter_ = 0;
     * used in PApplet setup
     */
    private static PApplet app_;
    private static int appSetCounter_ = 0;

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

        app_.translate(bx_, by_, bz_);
        app_.color(0,250,0);
        app_.noStroke();
        app_.box(40,20,50);
        app_.popMatrix();
    }

    /**
     * update dt, time in seconds, since the last update.
     * Hervé - we'll use the object's instance variable to access the
     * applications instance methods and variables.
     */
    public void update(float dt){

        if (bz_ <= rad_) {
            Vz_ = refl_ * PApplet.abs(Vz_);
            Vx_ *= refl_;
            Vy_ *= refl_;

            if (PApplet.abs(Vx_) < ZERO_SPEED)
                Vx_ = 0.f;
            if (PApplet.abs(Vy_) < ZERO_SPEED)
                Vy_ = 0.f;
            if (PApplet.abs(Vz_) < ZERO_SPEED)
                Vz_ = 0.f;

        }

        float halfdt2 = 0.5f * dt*dt;

        bx_ += Vx_ * dt;
        by_ += Vy_ * dt;
        bz_ += Vz_ * dt - G*halfdt2;

        Vz_ -= G * dt;

    }


    /**
     * We need to have a method that detects when the brick is touched
     * by the monster. If the monster lands on top we should stop the brick
     * and create and game continues. If the brick hits the monster from the side,
     * the game should stop. This task will be handled by isInside()
     * @param thY
     * @param theX
     * @return
     */
    public boolean isInside(float thY, float theX){
        return false;
    }

    // And I use my static counter
    //	to let the variable be set only once.
    protected static int setup(PApplet theApp)
    {
        if (appSetCounter_ == 0)
        {
            app_ = theApp;
            appSetCounter_ = 1;
        }
        else
            appSetCounter_ = 2;

        return appSetCounter_;

    }
}
