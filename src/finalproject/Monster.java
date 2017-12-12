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
    private float bx_ = 50, by_ = 0, bz_ = 40;
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

    /**
     * The Monster will need to keep track of its own location which the main will update.
     */
    public Monster(){

    }

    /**
     * Hervé week07, use objects instance variable to access the application's
     * instance methods and variables
     */
    public void draw(PApplet app_){

        app_.pushMatrix();
        app_.translate(bx_, by_, bz_);
        app_.noStroke();
        app_.fill(255,0,255);
        app_.sphere(rad_);
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
    
    public float getX() {
    	return bx_;
    }
    
    public float getY() {
    	return by_;
    }
    
    
    public float getZ() {
    	return bz_;
    }
    

    /**
     * We need to have a method that detects when the Monster is touched
     * by the brick. If the monster lands on top we should stop the brick
     * and create and game continues. If the brick hits the monster from the side,
     * the game should stop.
     * @param thY
     * @param theX
     * @return
     * (Do we need this method in Monster or Brick, or both)
     */
    public boolean isInside(float thY, float theX){
        return false;
    }

    //	After telling you that protected was barely any better than public, I use it here.  Why?
  	//	Well, the limited additional protection is better than nothing.  And I use my static counter
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
