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

  public Client(Point2D location, Point2D destination, String email, String password, String name, String address, String birthday, double spent){
    super(email, password, name, address, birthday);
    this.favoriteDrivers = new TreeMap<String,Driver>();
    this.favoriteVehicles = new TreeMap<String,Vehicle>();
    this.spent = spent;
    this.location = location;
    this.destination = destination;
  }

  public void setLocation(double x, double y){
    this.location = new Point2D(x,y);
  }

  public void setDestination(double x, double y){
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

  public TreeMap<String, Driver> getFavoriteDrivers(){
    TreeMap<String, Driver> neo = new TreeMap<String, Driver>();
    for(Map.Entry<String, Driver> entrys : this.favoriteDrivers.entrySet()){
      neo.put(entrys.getKey(), entrys.getValue());
    }
    return neo;
  }

  public TreeMap<String, Vehicle> getFavoriteVehicles(){
    TreeMap<String, Vehicle> neo = new TreeMap<String, Vehicle>();
    for(Map.Entry<String, Vehicle> entrys : this.favoriteVehicles.entrySet()){
      neo.put(entrys.getKey(), entrys.getValue());
    }
    return neo;
  }

  public void setFavoriteDrivers(TreeMap<String, Driver> neo){
    for(Map.Entry<String, Driver> entrys : neo.entrySet()){
      this.favoriteDrivers.put(entrys.getKey(), entrys.getValue());
    }
  }

  public void setFavoriteVehicles(TreeMap<String, Vehicle> neo){
    for(Map.Entry<String, Vehicle> entrys : neo.entrySet()){
      this.favoriteVehicles.put(entrys.getKey(), entrys.getValue());
    }
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
    Client c = new Client(this.getLocation(), this.getDestination(), this.getEmail(), this.getPassword(), this.getName(), this.getAddress(), this.getBirthday(), this.getMoneySpent());
    c.setFavoriteVehicles(this.getFavoriteVehicles());
    c.setFavoriteDrivers(this.getFavoriteDrivers());
    return c;
  }

}
