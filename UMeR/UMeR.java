import java.util.Hashtable;
/**
 * Write a description of class UMeR here.
 *
 * @author jhbb
 * @version 22/04
 */
public class UMeR{
  private int numberOfVehicles;
  private int numberOfDrivers;
  private Hashtable<Vehicle, Integer> vehicles;
  private Hashtable<Driver, String> drivers;
  private Hashtable<Taxi, Integer> taxis;

  public UMeR(){
    this.numberOfVehicles = 0;
    this.numberOfDrivers = 0;
    this.vehicles = new Hashtable<Vehicle, Integer>(100, 0.75f);
    this.drivers = new Hashtable<Driver, String>(100, 0.75f);
    this.taxis =  new Hashtable<Taxi,Integer>(100, 0.75f);
  }

}
