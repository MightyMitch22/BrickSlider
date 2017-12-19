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
    private ArrayList<Brick> brickList;
    private Brick brick;
    private Brick prevBrick;
    private Monster monster;
    //private PFont font; //font for score

    /**
     * Various status variables
     */
    //-----------------------------
    //	Various status variables
    //-----------------------------
    private float lastTime;
    private int frameIndex = 0;
    private boolean animate = false;
    private int score = 0;

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

    private boolean gameOver = false;

    /**
     * Setup includes: FrameRate, keyFrames, Camera, Textures, objects
     */
    public void setup() {

        //Here sets the frame rate
        //amount of time it resets per second.
        frameRate(600);

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

        brickList = new ArrayList<Brick>();
        brick = new Brick(0);
        brickList.add(brick);
        prevBrick = null;

//        font = createFont("LetterGothicStd.ttf", 32);
//        textFont(font);

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

    public void setGameOver() {
        gameOver = true;

        System.exit(0);
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

            textSize(15);
            fill(0, 102, 153);
            text(score, 5, 70, 5);
            fill(0, 102, 153);
            text("Jump Score", 1, 85, 5);


            //Enable camera so it follows the ball
            camera(eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);


            monster.draw(this);
            //update where the camera should be depending on location
            //of the ball
            centerX = monster.updateCameraX();
            centerY = monster.updateCameraY();
            centerZ = monster.updateCameraZ();

            //draw the next brick in the array list
            for (Brick b: brickList)
                b.draw(this);

        }

        int t = millis();

        if (animate) {
            //isTouching();

            float dt = (t - lastTime) * 0.001f;

            int monsterLanded = monster.update(dt, this, brick, prevBrick);
            //If the brick is touched, stop moving brick

            //brick movement, list management
            boolean brickOut = brick.update(dt);
            if (brickOut) {
                brickList.remove(brick);
                brick = new Brick(brickList.size());
                brickList.add(brick);

            }

            if (monsterLanded == 1) {
                prevBrick = brick;
                brick = new Brick(brickList.size());
                System.out.println("Created new brick at " + brick.getbx() + "  z = " + brick.getbz());
                System.out.println("        Old brick at " + prevBrick.getbx() + "  z = " + prevBrick.getbz());
                brickList.add(brick);
                score++;
            }

            lastTime = t;
        }
    }


    /**
     * DrawSurface will create the stage for our game
     */
    public void drawSurface() {

        beginShape(QUADS);

        vertex(XMIN, YMAX, 0, 0, 0);
        vertex(XMIN, YMIN, 0, 0, 1);
        vertex(XMAX, YMIN, 0, 1, 0);
        vertex(XMAX, YMAX, 0, 1, 1);

        endShape(CLOSE);
    }



    public void keyPressed() {
        switch (key) {

            case 'p': //'p' for play
                animate = true;
                lastTime = millis();

                break;
            case 'c':
                animate = false;
                //FileInOutMachine.saveKeyFramesToFile(keyFrames);
                break;
            case 'j':
                monster.jump();
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
            case 'v':
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
