package finalproject;

import processing.core.PApplet;

import java.util.ArrayList;


/**
 * 3D
 * Created by Mitch Blier, Marissa Gagnier on 11/29/17.
 */
public class Main extends PApplet implements ApplicationConstants {

	
    private static final long serialVersionUID = 1L;
    

    /**
     * Graphical objects
     * Our main ones are..
     * The keyframes, our bricks
     * and also our monster
     */
    //-----------------------------
    //	graphical objects
    //-----------------------------
    private ArrayList<KeyFrame> keyFrames;
    private Brick brick;
    private Monster monster;

    /**
     * Various status variables
     */
    //-----------------------------
    //	Various status variables
    //-----------------------------
    private float lastTime;
    private int frameIndex = 0;
    private boolean animate = false;
    private boolean brickTouched = false;
    private int numPressed = 0;
    /**
     * Camera Functionality
     * (setting the eye position, the center of the scene,
     * and which axis is facing upward)
     */
    //-----------------------
    //    CAMERA
    //-----------------------
    private float eyeX = 0;
    private float eyeY = 200;
    private float eyeZ = 100;
    //---
    //always use negative z so it is upright
    //---
    //(0,0,0) updating the camera here to stay with the monster and not zero always
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
     * Setup includes: FrameRate, keyFrames, Camera, Textures, objects
     */
    public void setup() {

        //Here sets the frame rate
        //amount of time it resets per second.
        frameRate(600);

        //here I create my arrayList of keyFrames in order to add the animation
        keyFrames = new ArrayList<KeyFrame>();
        keyFrames.add(new KeyFrame(1/*time*/, 1/*x*/, 1/*y*/, 1/*angle*//* might need an arrayList*/));

        textureMode(NORMAL);


        //this camera stuff tells where the camera is looking and may need to be changed
        //the up vector for example.
        //where is the camera in the world are the first three
        //the second one...is the angle of the frame left and right
        //the third is where the up is for the camera
        //the last one is -1 because processing starts at negative
        camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);

        //draw the new monster and brick
        monster = new Monster();
        brick = new Brick();

        lastTime = millis();
        
   
    }

    /**
     * Settings will create the window in which the
     * application will take place
     * *
     */
    public void settings() {

        //Initial Scene configuration
        size(WINDOW_WIDTH, WINDOW_HEIGHT, P3D); //Eventually add P3D

    }

    /**
     * Draw creates our keyFrames and updates our Game
     */
    public void draw() {

        frameIndex++;

        if (frameIndex % 4 == 0) {
            background(153, 255, 255);
            lights();
            fill(255, 255, 153);
            drawSurface();
          

            //Enable camera so it follows the ball
            //camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);


            monster.draw(this);
            //update where the camera should be depending on location
            //of the ball
            centerX = monster.updateCameraX();
            // System.out.println("Print cam X:" + centerX);
            centerY = monster.updateCameraY();
            // System.out.println("Print cam Y:" + centerY);
            centerZ = monster.updateCameraZ();
            // System.out.println("Print cam Z:" + centerZ);

            brick.draw(this);

        }

        int t = millis();

        if (animate) {
            //isTouching();

            float dt = (t - lastTime) * 0.001f;
            monster.update(dt, brick);
            //If the brick is touched, stop moving brick
            brick.update(dt);

            //If the ball is on top of the brick stop,
            //isTouching();

            lastTime = t;
        }
    }


    /**
     * DrawSurface will create the stage for our game
     */
    public void drawSurface() {
        beginShape(QUADS);
        //texture(backgroundImage_);

        vertex(XMIN, YMAX, 0, 0, 0);
        vertex(XMIN, YMIN, 0, 0, 1);
        vertex(XMAX, YMIN, 0, 1, 0);
        vertex(XMAX, YMAX, 0, 1, 1);

        endShape(CLOSE);
    }



    public void keyPressed() {
        switch (key) {
        //start with key press, and then when touches
        //the brick it stops, 
        //when key pressed start bouncing again
        
            //my animation is started here
            case 'v':
            	
            	numPressed ++;
                if(monster.bounceOnce(brick,animate) == false) {
                //odd number animate on 
                	animate = false;
                }
                if(numPressed%2 == 0) {
                	animate = true;
                //even number animate off
                }
                break;
            case 'c':
                animate = false;
                //FileInOutMachine.saveKeyFramesToFile(keyFrames);
                break;
            case 'k':
                //brickTouched = true;
                //snapCurrent();
                break;
            case 'u':
                //brickTouched = false;
                //body.moveUp;
                break;
            case 'l':
                //moveLeft();
                break;
            case 'o':
                //moveRight();
                break;
            case 'q':
                //moveDown();
                break;
            case 'p':
                //moveRight();
                break;
            case 'y':
                //moveRight();
                break;
            case 'z':
                //do nothing
                break;
        }

    }

    /**
     * Helps allign the text on the screen
     * @param x
     */
    void drawType(float x) {
    	  line(x, 0, x, 65);
    	  line(x, 220, x, height);
    	  fill(0);
    	  text("ichi", x, 95);
    	  fill(51);
    	  text("ni", x, 130);
    	  fill(204);
    	  text("san", x, 165);
    	  fill(255);
    	  text("shi", x, 210);
    	}
    
    /**
     * Main creates out PApplet for our scene
     *
     * @param argv ummm
     */
    public static void main(String[] argv) {
        PApplet.main("finalproject.Main");
    }

}
