package finalproject;

import java.util.ArrayList;

/**
 * KeyFrame holds all the variables for
 * what should be done at a KeyFrame.
 * Created by Mitch on 11/22/17.
 */


public class KeyFrame{

    //
    int t; //time

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

    public int getTime(){

        return t;

    }

    public float getX(){

        return keyX;

    }

    public float getY(){

        return keyY;

    }

    public float getAngle(){

        return keyAngle;

    }

    public  ArrayList<ArrayList<Float>> getbrickAnimation(){

        return brickAnimation;

    }

}
