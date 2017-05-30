import java.io.Serializable;

/**
 * Write a description of class Van here.
 *
 * @author jhbb
 * @version 28/04
 */
public class Van extends Vehicle
{
  public static int seats = 8;

  public Van(double speed, double factor, String plate){
    super(speed, factor, plate);
  }

  public Van(Van c){
    super(c);
  }

  public Van clone(){
    return new Van(this);
  }
}
