package finalproject;

import processing.core.PApplet;
import processing.core.PImage;
import java.math.*;
import java.util.ArrayList;


/**
 *3D
 * Created by Mitch Blier, Marissa Gagnier on 11/29/17.
 */
public class Main extends PApplet implements ApplicationConstants {


  private static final long serialVersionUID = 1L;

  //-----------------------------
  //	graphical objects
  //-----------------------------
  private ArrayList<KeyFrame> keyFrames;
  private Brick brick;
  private Monster ball;

  
  //-----------------------------
  //	Various status variables
  //-----------------------------
  private long frame_ = 0L;
  
  
  

  //-----------------------------
  //    CAMERA
  //----------------------------
  //---
  //
  //---
   private float eyeX = 200;
   private float eyeY = 0;
   private float eyeZ = 100;
   //---
   //always use negative z so it is upright
   //---
   private float centerX = 0;
   private float centerY = 0;
   private float centerZ = 0;
   //---
   //always use negative z so it is upright
   //---
   private float upX = 0;
   private float upY = 0;
   private float upZ = -1;


/**
 * Camera Functionality
 * (setting the eye position, the center of the scene, and which axis is facing upward)
 */
 public void setup() {
	
	//Here sets the rate of the framerate
	 //amount of time it resets per second.
	frameRate(600);
	
	//here I create my arrayList of keyFrames in order to add the animation
	keyFrames =  new ArrayList<KeyFrame>();
	keyFrames.add(new KeyFrame(1/*time*/,1/*x*/,1/*y*/,1/*angle*//* might need an arrayList*/));
	 
	textureMode(NORMAL);

	//this camera stuff tells where the camera is looking and may need to be changed
	//the up vector for example.
	//where is the camera in the world are the first three
	//the second one...is the angle of the frame left and right
	//the third is where the up is for the camera
	//the last one is -1 because processing starts at negative
	camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);

	//where i draw the new body and brick
	ball = new Monster();
	brick = new Brick(keyFrames);
 }
/**
 * settings will create the window in which the
 * app will take place
 * *
 */
  public void settings(){

    //Initial Scene configuration
    size(WINDOW_WIDTH, WINDOW_HEIGHT, P3D); //Eventually add P3D

  }

  public void draw() {
		background(0,200,0);
		lights();
		fill(255,0,0);
		drawSurface();

		fill(0,0,255);
		ball.draw(this);

		fill(0,255,0);
		brick.draw(this);
  }

  /**
   * drawSurface will create the ground where the monster will stand
   *
   */
  public void drawSurface(){
    beginShape(QUADS);
    //texture(backgroundImage_);

    vertex(XMIN, YMAX, 0, 0, 0);
    vertex(XMIN, YMIN, 0, 0, 1);
    vertex(XMAX, YMIN, 0, 1, 0);
    vertex(XMAX, YMAX, 0, 1, 1);

    endShape(CLOSE);
  }


    public static void main(String[] argv)
    {
        PApplet.main("finalproject.Main");
    }

}
