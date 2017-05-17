import java.util.*;
/**
 * Write a description of class Client here.
 *
 * @author jhbb
 * @version 02/05
 */
public class Client extends Person
{

  //instance variables
  private Point2D location;
  private Point2D destination;
  private double spent;
  private Map<String,Driver> favoriteDrivers;
  private Map<String,Vehicle> favoriteVehicles;
  //TODO TaxiRide list

  public Client(String email, String password, String name, String address, String birthday, double spent){
    super(email, password, name, address, birthday);
    this.spent = spent;
    this.favoriteDrivers = new TreeMap<String,Driver>();
    this.favoriteVehicles = new TreeMap<String,Vehicle>();
  }

  public Client(Client c){
    super(c);
    this.spent = c.getMoneySpent();
    this.favoriteDrivers = new TreeMap<String,Driver>();
    this.favoriteVehicles = new TreeMap<String,Vehicle>();
  }

  public void addLocation(double x, double y){
    this.location = new Point2D(x,y);
  }

  public void addDestination(double x, double y){
    this.destination = new Point2D(x,y);
  }

  public Point2D getLocation(){
    return this.location;
  }

  public Point2D getDestination(){
    return this.destination;
  }

  public double getMoneySpent(){
    return this.spent;
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

  public void addDriver(Driver neo) throws UserExistsException{
    if(this.favoriteDrivers.containsKey(neo.getEmail())) throw new UserExistsException("Motorista já existente");
    this.favoriteDrivers.put(neo.getEmail(), neo);
    System.out.println(this.favoriteDrivers.get(neo.getEmail()));
  }

  public void addVehicle(Vehicle neo) throws VehicleExistsException{
    if(this.favoriteVehicles.containsKey(neo.getPlate())) throw new VehicleExistsException("Veículo já existente");
    this.favoriteVehicles.put(neo.getPlate(), neo);
  }

  public void spendMoney(double value){
    this.spent+=value;
  }

  public void callTaxi(Taxi t){
    t.enqueue(this);
  }

  public Client clone(){
    return new Client(this);
  }

}
