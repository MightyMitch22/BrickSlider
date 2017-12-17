package finalproject;

import processing.core.PApplet;

public abstract class GraphicObject implements ApplicationConstants {

	//-----------------------------
  	//	Various status variables
  	//-----------------------------
    private float x, y, angle;
    private float cosAngle;
    private float sinAngle;
    private float r, g, b;
    private boolean isSelected;
    public static final float ANGLE_INCR = 0.05f;
	
    /**
     * private static PApplet app;
     * private static int appSetCounter = 0;
     * used in PApplet setup
     */
    protected static PApplet app;
    private static int appSetCounter = 0;

    /**
     * Constructor for the type: Graphical Object
     * Parameters are (x,y,angle)
     */
    @SuppressWarnings("static-access")
	public GraphicObject(float xval, float yval, float angleval) {
        x = xval;
        y = yval;
        angle = angleval;
        cosAngle = app.cos(angle);
        sinAngle = app.sin(angle);
        isSelected = false;

        // Here, since we have a reference to the app (as a static variable),
        //we can use directly its simpler random() method.
        r = app.random(255);
        g = app.random(255);
        b = app.random(255);
    }

    /**
     * Constructor for the type: Graphical Object
     * Parameters are default
     */
    public GraphicObject() {

        x = (float) (WORLD_X_MIN + (WORLD_X_MAX - WORLD_X_MIN) * Math.random());
        y = (float) (WORLD_Y_MIN + (WORLD_Y_MAX - WORLD_Y_MIN) * Math.random());
        angle = (float) (2*Math.random()*Math.PI);

        // Here, since we have a reference to the app (as a static variable),
        //we can use directly its simpler random() method.
        r = app.random(255);
        g = app.random(255);
        b = app.random(255);
    }

    /**
     * sets color of graphical object
     */
    public void setColor(float rval, float gval, float bval) {
        r = rval;
        g = gval;
        b = bval;
    }


    /**
     * sets position of graphical object
     */
    public void setPose(float x, float y, float angle) {
        x = x;
        y = y;
        angle = angle;
    }

    /**
     * returns current X
     */
    public float getX() {
        return x;
    }
    
    /**
     * returns current Y
     */
    public float getY() {
        return y;
    }
    
    /**
     * returns current Angle
     */
    public float getAngle() {
        return angle;
    }
    
    /**
     * returns current SinAngle
     */
    public float getSinAngle() {
        return sinAngle;
    }
    
    /**
     * returns current CosAngle
     */
    public float getCosAngle() {
        return cosAngle;
    }

    /**
     * sets Fill color of object
     */
    public void setFillingColor() {
        app.fill(r, g, b);
    }

    /**
     * returns boolean if selected:true
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * sets boolean if selected:true
     */
    public void setSelected(boolean val) {
        isSelected = val;
    }

    /**
     * sets angle of rotation +
     */
    public void rotatePlus() {
        angle += ANGLE_INCR;
    }

    /**
     * sets angle of rotation -
     */
    public void rotateMinus() {
        angle -= ANGLE_INCR;
    }

    /**
     * returns boolean if inside with parameters
     */
    public abstract boolean isInside(float theY, float theX);

    /**
     * draws a refereance frame to the current world
     */
    public static  void drawReferenceFrame()
    {
        app.strokeWeight(PIXELS_TO_WORLD_SCALE);
        app.stroke(255, 0, 0);
        app.line(0, 0, WORLD_WIDTH/20, 0);
        app.stroke(0, 255, 0);
        app.line(0, 0, 0, WORLD_WIDTH/20);
    }

    /**
     * Draw method for a graphic object
     * They all have one
     */
    public abstract void draw();

    /**	This static method is invoked from the main class to tell this graphic class
     * "Hey, I'm your app!"  This is one way to know that.  Another one is for the running
     * sketch to send a reference to itself to the <code>draw</code> method and any other method
     * that may need it.  I am not taking positions here.  It's really a matter of style preferences
     * (in Processing, where there should only be one app running.  In regular Java, where there could
     * be multiple frames/windows to render into, the "pass the reference every time" is pretty much mandated
     * by the API of the <code>paint</code> method.
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
