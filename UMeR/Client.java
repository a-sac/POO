
/**
 * Write a description of class Client here.
 *
 * @author jhbb
 * @version 20/04
 */
public class Client{

  //instance variables
  private Point2D location;
  //TODO Travel list

  public Client(){
    this.location = new Point2D();
  }

  public Client(Point2D point){
    this.location = point.getLocation();
  }

  
}
