package finalproject;

/**
 *3D
 * Created by Mitch Blier, Marissa Gagnier on 11/29/17.
 */
public class Main {
  //-----------------------------
  //	graphical objects
  //-----------------------------
  private Rectangle Brick;






/**
 * settings will create the window in which the
 * app will take place
 * *
 */
  public void settings(){

    //Initial Scene configuration
    size(WINDOW_WIDTH, WINDOW_HEIGHT, P3D);

  }
  /**
   * drawSurface will create the ground where the monster will stand
   *
   */
  public void drawSurface(){
    beginShape(QUADS);
    texture(backgroundImage_);

    vertex(XMIN, YMAX, 0, 0, 0);
    vertex(XMIN, YMIN, 0, 0, 1);
    vertex(XMAX, YMIN, 0, 1, 0);
    vertex(XMAX, YMAX, 0, 1, 1);

    endShape(CLOSE);
  }

}
