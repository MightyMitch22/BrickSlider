package finalproject;

import processing.core.PApplet;
import processing.core.PImage;
import java.math.*;

/**
 *3D
 * Created by Mitch Blier, Marissa Gagnier on 11/29/17.
 */
public class Main extends PApplet implements ApplicationConstants {
	
	
  private static final long serialVersionUID = 1L;
  
  //-----------------------------
  //	graphical objects
  //-----------------------------
  private Brick brick;
  private TestBall ball;

  
  //-----------------------------
  //    CAMERA 
  //----------------------------
  //---
  //
  //---
   private float eyeX = 200;
   private float eyeY = -200;
   private float eyeZ = 25;
   //---
   //always use negative z so it is upright
   //---
   private float centerX = 0; 
   private float centerY = 0; 
   private float centerZ = -100; 
   //---
   //always use negative z so it is upright
   //---
   private float upX = 0;
   private float upY = 0;
   private float upZ = -2;
  
  
/**
 * Camera Functionality
 * (setting the eye position, the center of the scene, and which axis is facing upward)
 */
 public void setup() {
	textureMode(NORMAL);
	
	//this camera stuff tells where the camera is looking and may need to be changed
	//the up vector for example. 
	//where is the camera in the world are the first three
	//the second one...is the angle of the frame left and right
	//the third is where the up is for the camera
	//the last one is -1 because processing starts at negative
	camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ); 
	
	ball = new TestBall();
	brick = new Brick();
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
		background(100,0,0);
		lights();

		drawSurface();

		ball.draw(this);

		brick.draw(this);
  }
  
  /**
   * drawSurface will create the ground where the monster will stand
   *
   */
  public void drawSurface(){
    beginShape(QUADS);
    //texture(backgroundImage_);

    fill(255,0,0);

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
