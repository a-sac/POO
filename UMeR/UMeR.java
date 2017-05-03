import java.util.*;
/**
 * Write a description of class UMeR here.
 *
 * @author jhbb
 * @version 02/05
 */
public class UMeR{
  private Integer idCounter;
  private Map<String, Vehicle> vehicles;
  private Map<String, Driver> drivers;
  private TreeSet<Taxi> taxis;

  public UMeR(){
    this.idCounter = 0;
    this.vehicles = new TreeMap<String, Vehicle>();
    this.drivers = new TreeMap<String, Driver>();
  }

  public void addVehicle(Vehicle neo){
    this.vehicles.put(neo.getPlate(), neo);
  }

  public void addDriver(Driver neo){
    this.drivers.put(neo.getEmail(), neo);
  }

  public Taxi getClosestTaxi(){
    return this.taxis.first();
  }

  public void startDay(){
    this.taxis = new TreeSet<Taxi>(new TaxiComparator());
    Iterator<Vehicle> v = this.vehicles.values().iterator();
    Iterator<Driver> d = this.drivers.values().iterator();
    while(d.hasNext() && v.hasNext()){
      this.taxis.add(new Taxi(this.idCounter, d.next(), v.next()));
      d.remove();
      v.remove();
    }
  }

  public void endDay(){
    Iterator<Taxi> t = this.taxis.iterator();
    Map<String, Vehicle> vehicles = new TreeMap<String, Vehicle>();    Map<String, Driver> drivers = new TreeMap<String, Driver>();
    while(t.hasNext()){
      drivers.put(t.next().getDriver().getEmail(), t.next().getDriver());
      vehicles.put(t.next().getVehicle().getPlate(), t.next().getVehicle());
      t.remove();
    }
    this.vehicles = vehicles;
    this.drivers = drivers;
  }
}
