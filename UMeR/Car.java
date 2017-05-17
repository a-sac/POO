
/**
 * Write a description of class Car here.
 *
 * @author jhbb
 * @version 28/04
 */
public class Car extends Vehicle
{

  public static int seats = 4;

  public Car(double speed, double factor, String plate){
    super(speed, factor, plate);
  }

  public Car(Car c){
    super(c);
  }

  public Car clone(){
    return new Car(this);
  }
}
