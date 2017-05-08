import java.util.*;
/**
 * Write a description of class Client here.
 *
 * @author jhbb
 * @version 02/05
 */
public class Client extends Person{

  //instance variables
  private Point2D location;
  private Point2D destination;
  private double balance;
  private Map<String,Driver> favoriteDrivers;
  private Map<String,Vehicle> favoriteVehicles;
  //TODO TaxiRide list

  public Client(Point2D location, Point2D destination, String email, String password, String name, String address, String birthday, double balance){
    super(email, password, name, address, birthday);
    this.location = location;
    this.destination  = destination;
    this.balance = balance;
  }

  public Client(Client c){
    super(c);
    this.location = c.getLocation();
    this.destination = c.getDestination();
    this.balance = c.getBalance();
  }

  public Point2D getLocation(){
    return this.location;
  }

  public Point2D getDestination(){
    return this.destination;
  }

  public double getBalance(){
    return this.balance;
  }

  public Map<String, Driver> getFavoriteDrivers(){
    Map<String, Driver> neo = new TreeMap<String, Driver>();
    for(Map.Entry<String, Driver> entrys : this.favoriteDrivers.entrySet()){
      neo.put(entrys.getKey(), entrys.getValue());
    }
    return neo;
  }

  public Map<String, Vehicle> getFavoriteVehicles(){
    Map<String, Vehicle> neo = new TreeMap<String, Vehicle>();
    for(Map.Entry<String, Vehicle> entrys : this.favoriteVehicles.entrySet()){
      neo.put(entrys.getKey(), entrys.getValue());
    }
    return neo;
  }

  public void printFavoriteDrivers(){
    for(String email : this.favoriteDrivers.keySet()){
      System.out.println(email);
    }
  }

  public void printFavoriteVehicles(){
    for(String plate : this.favoriteVehicles.keySet()){
      System.out.println(plate);
    }
  }

  public void addDriver(Driver neo){
    this.favoriteDrivers.put(neo.getEmail(), neo);
  }

  public void addVehicle(Vehicle neo){
    this.favoriteVehicles.put(neo.getPlate(), neo);
  }

  public void spendMoney(double value){
    this.balance+=value;
  }

  public void callTaxi(Taxi t){
    t.enqueue(this);
  }

  public Client clone(){
    return new Client(this);
  }

}
