package week07.handout;

import processing.core.PApplet;

/** Graphic class to draw a face
 *
 * @author jyh
 *
 */
public class LinearInterpolationFace implements ApplicationConstants
{
	public static final float FACE_DIAMETER = 5;
	public static final float EAR_DIAMETER = FACE_DIAMETER/2;
	public static final float EYE_OUTER_DIAMETER = FACE_DIAMETER/4;
	public static final float EYE_INNER_DIAMETER = FACE_DIAMETER/6;
	public static final float MOUTH_V_DIAMETER = FACE_DIAMETER/4;
	public static final float MOUTH_H_DIAMETER = FACE_DIAMETER/2.5f;
	//
	public static final float EAR_V_OFFSET = 0.3f*FACE_DIAMETER;
	public static final float EAR_H_OFFSET = 0.45f*FACE_DIAMETER;
	public static final float EYE_V_OFFSET = 0.2f*FACE_DIAMETER;
	public static final float EYE_H_OFFSET = 0.2f*FACE_DIAMETER;
	//
	private static final float LEFT_EAR_X = -EAR_H_OFFSET;
	private static final float LEFT_EAR_Y = EAR_V_OFFSET;
	private static final float RIGHT_EAR_X = EAR_H_OFFSET;
	private static final float RIGHT_EAR_Y = EAR_V_OFFSET;
	//
	private static final float LEFT_EYE_X = -EYE_H_OFFSET;
	private static final float LEFT_EYE_Y = EYE_V_OFFSET;
	private static final float RIGHT_EYE_X = EYE_H_OFFSET;
	private static final float RIGHT_EYE_Y = EYE_V_OFFSET;

	private static final float MOUTH_H_OFFSET = 0.f;
	private static final float MOUTH_V_OFFSET = -FACE_DIAMETER*0.1f;

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

		// Here, since we have a reference to the app (as a static variable),
		//	we can use directly its simpler random() method.
		r_ = app_.random(255);
		g_ = app_.random(255);
		b_ = app_.random(255);

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

		//		boolean drawRelative = app_.keyPressed && app_.key == 'r';
		//		boolean drawAbsolute = app_.keyPressed && app_.key == 'a';

		app_.translate(x_,  y_);
		app_.rotate(angle_);
		app_.scale(scale_);
		app_.noStroke();
		app_.fill(r_, g_, b_);
		app_.ellipse(0, 0, FACE_DIAMETER, FACE_DIAMETER);

		//	draw the left ear
		app_.pushMatrix();
		app_.noStroke();
		app_.fill(r_, g_, b_);
		app_.translate(LEFT_EAR_X, LEFT_EAR_Y);
		app_.ellipse(0, 0, EAR_DIAMETER, EAR_DIAMETER);
		app_.popMatrix();

		//	draw the right ear
		app_.pushMatrix();
		app_.noStroke();
		app_.fill(r_, g_, b_);
		app_.translate(RIGHT_EAR_X, RIGHT_EAR_Y);
		app_.ellipse(0, 0, EAR_DIAMETER, EAR_DIAMETER);
		app_.popMatrix();

		//	draw the left and right eyes (I could have gone the Push&pop way here as
		//	well, and would if the eyes were more complex, but here they are simply
		//	ellipses, so no need to make it over-complex.
		app_.fill(255);
		app_.ellipse(LEFT_EYE_X, LEFT_EYE_Y, EYE_OUTER_DIAMETER, EYE_OUTER_DIAMETER);
		app_.ellipse(RIGHT_EYE_X, RIGHT_EYE_Y, EYE_OUTER_DIAMETER, EYE_OUTER_DIAMETER);
		app_.fill(0);
		app_.ellipse(LEFT_EYE_X, LEFT_EYE_Y, EYE_INNER_DIAMETER, EYE_INNER_DIAMETER);
		app_.ellipse(RIGHT_EYE_X, RIGHT_EYE_Y, EYE_INNER_DIAMETER, EYE_INNER_DIAMETER);

		// draw the mouth
		app_.stroke(0);
		app_.noFill();
		app_.strokeWeight(2*PIXEL_TO_WORLD);
		app_.arc(MOUTH_H_OFFSET, MOUTH_V_OFFSET,
				MOUTH_H_DIAMETER, MOUTH_H_DIAMETER, -7*PApplet.PI/8, -PApplet.PI/8);

		app_.popMatrix();
	}

	//	dt: time (in seconds) since the last update
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
