package finalproject;

import processing.core.PApplet;

/** 
 * Graphic class to animate a monster and a Brick
 */
public class LinearInterpolationFace implements ApplicationConstants
{

	//-----------------------------
	//	Various status variables
	//-----------------------------
	private static final int TIME_IDX = 0, X_IDX = 1, Y_IDX = 2, A_IDX = 3, S_IDX = 4;
	private float [][]keyFrame;
	
	/**
	 * x coordinate of the object's center
	 */
	private float x;

	/**
	 * y coordinate of the object's center
	 */
	private float y;

	/**
	 * objects angle
	 */
	private float angle;

	/**
	 * objects color
	 */
	private float r, g, b;

	/**
	 * objects scale
	 */
	private float scale;

	/**
	 * objects time variable
	 */
	private float t;


    /**
     * private static PApplet app;
     * private static int appSetCounter = 0;
     * used in PApplet setup
     */
	private static PApplet app;
	private static int appSetCounter = 0;
	

	/**
	 * Constructor for linearInterpolationFace
	 */
	public LinearInterpolationFace(float[][] keyFrames) {
		keyFrame = keyFrames;
		t = 0;
		// initial state: first keyFrame
		x = keyFrame[0][X_IDX];
		y = keyFrame[0][Y_IDX];
		angle = keyFrame[0][A_IDX];
		scale = keyFrame[0][S_IDX];
	}


	/**	renders the Face object
	 *The Face class already has a static reference to the app.  I also pass the parameters that
     *I need for the rendering, namely the mode and the scale
	 */
	public void draw()
	{
		// we use this object's instance variable to access the application's instance methods and variables
		app.pushMatrix();
		app.popMatrix();
	}

	/**
	*updates each keyframe
	*/
	public void update(float dt)
	{
		t += dt;

		int segIndex = -1;

		// which keyframe interval are we within
		for (int i=0; i<keyFrame.length; i++)
		{
			if (t < keyFrame[i][TIME_IDX])
			{
				segIndex = i;
				break;
			}
		}

		//	we are within a keyframe segment
		if (segIndex > 0)
		{
			//	interpolate between frames segIndex-1 and segIndex
						// time since beginning of the keyframe segment
			float s = (t- keyFrame[segIndex-1][TIME_IDX]) /
						//	total duration of the keyframe segment
					(keyFrame[segIndex][TIME_IDX] - keyFrame[segIndex-1][TIME_IDX]);

												       //	x displacement over the keyframe segment
			x = keyFrame[segIndex-1][X_IDX] + s * (keyFrame[segIndex][X_IDX] - keyFrame[segIndex-1][X_IDX]);
			y = keyFrame[segIndex-1][Y_IDX] + s * (keyFrame[segIndex][Y_IDX] - keyFrame[segIndex-1][Y_IDX]);
			angle = keyFrame[segIndex-1][A_IDX] + s * (keyFrame[segIndex][A_IDX] - keyFrame[segIndex-1][A_IDX]);
			scale = keyFrame[segIndex-1][S_IDX] + s * (keyFrame[segIndex][S_IDX] - keyFrame[segIndex-1][S_IDX]);
		}
		else if (segIndex == -1)
		{
			x = keyFrame[keyFrame.length-1][X_IDX];
			y = keyFrame[keyFrame.length-1][Y_IDX];
			angle = keyFrame[keyFrame.length-1][A_IDX];
			scale = keyFrame[keyFrame.length-1][S_IDX];
		}

	}

	
    /**
     * We use the static counter
     * to let the variable be set only once.
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
