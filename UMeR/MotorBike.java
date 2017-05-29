import java.io.Serializable;

/**
 * Write a description of class MotorBike here.
 *
 * @author jhbb
 * @version 28/04
 */
public class MotorBike extends Vehicle implements Serializable
{

  public static int seats = 1;

  public MotorBike(double speed, double factor, String plate){
    super(speed, factor, plate);
  }

  public MotorBike(MotorBike c){
    super(c);
  }

  public MotorBike clone(){
    return new MotorBike(this);
  }

}
