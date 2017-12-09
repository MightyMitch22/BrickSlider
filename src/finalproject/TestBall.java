package finalproject;

import processing.core.PApplet;


public class TestBall implements ApplicationConstants{
	private float bx_ = 0, by_ = 0, bz_ = 10;
	private float Vx_ = 0, Vy_ = 0, Vz_ = -1;
	private float rad_ = 10;

	public TestBall() {
		
	}
	
	public void draw(PApplet app) {
		app.pushMatrix();
		
		app.translate(bx_, by_, bz_);
		app.noStroke();
		app.sphere(rad_);
		
		app.popMatrix();
	}
	
	public void update(float dt) {
		bx_ += Vx_ * dt;
		by_ += Vy_ * dt;
		bz_ += Vz_ * dt;		
	}
}
