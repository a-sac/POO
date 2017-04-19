
/**
 * Write a description of class Point2D here.
 *
 * @author jhbb
 * @version 1.0 19/04/2017
 */

public class Point2D {
  private int x;
  private int y;

  public Point2D(int x, int y){
    this.x = x;
    this.y = y;
  }

  public Point2D(){
    this.x = 0;
    this.y = 0;
  }

  public Point2D(Point2D p){
    this.x = p.getX();
    this.y = p.getY();
  }

  public int getX(){
    return this.x;
  }

  public int getY(){
    return this.y;
  }
}
