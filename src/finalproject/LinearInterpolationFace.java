package finalproject;

import processing.core.PApplet;

/** Graphic class to draw a face
 *
 * @author jyh
 *
 */
public class LinearInterpolationFace implements ApplicationConstants
{

	private static final int TIME_IDX = 0, X_IDX = 1, Y_IDX = 2, A_IDX = 3, S_IDX = 4;

	//   Here we store a reference to the app. in a static (aka "class") variable.
	private static PApplet app_;
	private static int appSetCounter_ = 0;

	private float [][]keyFrame_;
	/**
	 * x coordinate of the object's center
	 */
	private float x_;

	/**
	 * y coordinate of the object's center
	 */
	private float y_;

	//  angle
	private float angle_;

	//  color
	private float r_, g_, b_;

	// scale
	private float scale_;

	private float t_;

	public LinearInterpolationFace(float[][] keyFrames) {

		keyFrame_ = keyFrames;

		t_ = 0;
		// initial state: first keyFrame
		x_ = keyFrame_[0][X_IDX];
		y_ = keyFrame_[0][Y_IDX];
		angle_ = keyFrame_[0][A_IDX];
		scale_ = keyFrame_[0][S_IDX];
	}


	//	The Face class already has a static reference to the app.  I also pass the parameters that
	//  I need for the rendering, namely the mode and the scale
	/**	renders the Face object
	 *
	 * @param theMode	should the object be drawn with a bounding box?
	 */
	public void draw()
	{
		// we use this object's instance variable to access the application's instance methods and variables
		app_.pushMatrix();


		app_.popMatrix();
	}

	/**
	*
	*/
	public void update(float dt)
	{
		t_ += dt;

		int segIndex = -1;

		// which keyframe interval are we within
		for (int i=0; i<keyFrame_.length; i++)
		{
			if (t_ < keyFrame_[i][TIME_IDX])
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
			float s = (t_- keyFrame_[segIndex-1][TIME_IDX]) /
						//	total duration of the keyframe segment
					(keyFrame_[segIndex][TIME_IDX] - keyFrame_[segIndex-1][TIME_IDX]);

												       //	x displacement over the keyframe segment
			x_ = keyFrame_[segIndex-1][X_IDX] + s * (keyFrame_[segIndex][X_IDX] - keyFrame_[segIndex-1][X_IDX]);
			y_ = keyFrame_[segIndex-1][Y_IDX] + s * (keyFrame_[segIndex][Y_IDX] - keyFrame_[segIndex-1][Y_IDX]);
			angle_ = keyFrame_[segIndex-1][A_IDX] + s * (keyFrame_[segIndex][A_IDX] - keyFrame_[segIndex-1][A_IDX]);
			scale_ = keyFrame_[segIndex-1][S_IDX] + s * (keyFrame_[segIndex][S_IDX] - keyFrame_[segIndex-1][S_IDX]);
		}
		else if (segIndex == -1)
		{
			x_ = keyFrame_[keyFrame_.length-1][X_IDX];
			y_ = keyFrame_[keyFrame_.length-1][Y_IDX];
			angle_ = keyFrame_[keyFrame_.length-1][A_IDX];
			scale_ = keyFrame_[keyFrame_.length-1][S_IDX];
		}

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
