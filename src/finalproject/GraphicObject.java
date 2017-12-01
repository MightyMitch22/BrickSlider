package finalproject;

import processing.core.PApplet;

public abstract class GraphicObject implements ApplicationConstants {

    public static final float ANGLE_INCR = 0.05f;

    protected static PApplet app_;
    private static int appSetCounter_ = 0;

    private float x_, y_, angle_;
    private float cosAngle_;
    private float sinAngle_;
    private float r_, g_, b_;
    private boolean isSelected_;

    public GraphicObject(float x, float y, float angle) {
        x_ = x;
        y_ = y;
        angle_ = angle;
        cosAngle_ = app_.cos(angle_);
        sinAngle_ = app_.sin(angle_);
        isSelected_ = false;

        // Here, since we have a reference to the app (as a static variable),
        //	we can use directly its simpler random() method.
        r_ = app_.random(255);
        g_ = app_.random(255);
        b_ = app_.random(255);
    }

    public GraphicObject() {

        x_ = (float) (WORLD_X_MIN + (WORLD_X_MAX - WORLD_X_MIN) * Math.random());
        y_ = (float) (WORLD_Y_MIN + (WORLD_Y_MAX - WORLD_Y_MIN) * Math.random());
        angle_ = (float) (2*Math.random()*Math.PI);

        // Here, since we have a reference to the app (as a static variable),
        //	we can use directly its simpler random() method.
        r_ = app_.random(255);
        g_ = app_.random(255);
        b_ = app_.random(255);
    }

    public void setColor(float r, float g, float b) {
        r_ = r;
        g_ = g;
        b_ = b;
    }

    public void setPose(float x, float y, float angle) {
        x_ = x;
        y_ = y;
        angle_ = angle;
    }

    public float getX() {
        return x_;
    }
    public float getY() {
        return y_;
    }
    public float getAngle() {
        return angle_;
    }
    public float getSinAngle() {
        return sinAngle_;
    }
    public float getCosAngle() {
        return cosAngle_;
    }

    public void setFillingColor() {
        app_.fill(r_, g_, b_);
    }

    public boolean isSelected() {
        return isSelected_;
    }

    public void setSelected(boolean val) {
        isSelected_ = val;
    }

    public void rotatePlus() {
        angle_ += ANGLE_INCR;
    }

    public void rotateMinus() {
        angle_ -= ANGLE_INCR;
    }

    public abstract boolean isInside(float theY, float theX);

    public static  void drawReferenceFrame()
    {
        app_.strokeWeight(PIXELS_TO_WORLD_SCALE);
        app_.stroke(255, 0, 0);
        app_.line(0, 0, WORLD_WIDTH/20, 0);
        app_.stroke(0, 255, 0);
        app_.line(0, 0, 0, WORLD_WIDTH/20);
    }

    public abstract void draw();

    /**	This static method is invoked from the main class to tell this graphic class
     * "Hey, I'm your app!"  This is one way to know that.  Another one is for the running
     * sketch to send a reference to itself to the <code>draw</code> method and any other method
     * that may need it.  I am not taking positions here.  It's really a matter of style preferences
     * (in Processing, where there should only be one app running.  In regular Java, where there could
     * be multiple frames/windows to render into, the "pass the reference every time" is pretty much mandated
     * by the API of the <code>paint</code> method.
     *
     * @param theApp  <bf>the</bf> app we are running.
     * @return
     */
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
