package finalproject;

import java.util.ArrayList;

/**
 * KeyFrame holds all the variables for
 * what should be done at a KeyFrame.
 * Created by Mitch on 11/22/17.
 */
public class KeyFrame{

	//-----------------------------
	//	Various status variables
	//-----------------------------
    int t; 
    float keyX, keyY, keyAngle;
    
    ArrayList<ArrayList<Float>> brickAnimation = new ArrayList<ArrayList<Float>>();


    /**
     * Gather all the necessary information for a KeyFrame
     * @param time time at which the keyframe takes place
     * @param x the location of the keyframe x value
     * @param y the location of the keyframe y value
     * @param angle the angle of the object
     * @param brickAnimations is a float inside an array list of limbs, inside an array list
     */
    public KeyFrame(int time, float x, float y, float angle /* might need an param ArrayList<ArrayList<Float>> brickAnimations*/){
        t = time;
        keyX = x;
        keyY = y;
        keyAngle = angle;
        //brickAnimation = brickAnimations;
    }

    /**
     * returns current time
     */
    public int getTime(){

        return t;
    }

    /**
     * returns current X
     */
    public float getX(){

        return keyX;
    }

    /**
     * returns current Y
     */
    public float getY(){

        return keyY;
    }

    /**
     * returns current angle
     */
    public float getAngle(){

        return keyAngle;
    }

    /**
     * returns current brick animation
     */
    public  ArrayList<ArrayList<Float>> getbrickAnimation(){
        return brickAnimation;
    }

}
