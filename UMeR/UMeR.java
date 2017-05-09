import java.util.*;
/**
 * Write a description of class UMeR here.
 *
 * @author jhbb
 * @version 02/05
 */
public class UMeR{
  private Integer idCounter;
  private int userType; // 1 is client; 2 is driver
  private Map<String, Client> clients;
  private Map<String, Vehicle> vehicles;
  private Map<String, Driver> drivers;
  private TreeSet<Taxi> taxis;

  public UMeR(){
    this.idCounter = 0;
    this.clients = new TreeMap<String, Client>();
    this.vehicles = new TreeMap<String, Vehicle>();
    this.drivers = new TreeMap<String, Driver>();
  }

  public Map<String, Client> getClients() throws NoClientsException{
    if(this.clients.isEmpty()) throw new NoClientsException("No clients in database");
    else{
      Map<String, Client> neo = new TreeMap<String, Client>();
      for(Map.Entry<String, Client> entrys : this.clients.entrySet()){
        neo.put(entrys.getKey(), entrys.getValue());
      }
      return neo;
    }
  }


  public Map<String, Driver> getDrivers() throws NoDriversException{
    if(this.drivers.isEmpty()) throw new NoDriversException("No drivers in database");
    else{
      Map<String, Driver> neo = new TreeMap<String, Driver>();
      for(Map.Entry<String, Driver> entrys : this.drivers.entrySet()){
        neo.put(entrys.getKey(), entrys.getValue());
      }
      return neo;
    }
  }

  public Map<String, Vehicle> getVehicles(){
    Map<String, Vehicle> neo = new TreeMap<String, Vehicle>();
    for(Map.Entry<String, Vehicle> entrys : this.vehicles.entrySet()){
      neo.put(entrys.getKey(), entrys.getValue());
    }
    return neo;
  }

  public TreeSet<Taxi> getTaxis(){
    return this.taxis;
  }

  public void addVehicle(Vehicle neo) throws VehicleExistsException{
    if(this.vehicles.containsKey(neo.getPlate())) throw new VehicleExistsException("Vehicles alredy exists");
    this.vehicles.put(neo.getPlate(), neo);
  }

  public void addDriver(Driver neo) throws UserExistsException{
    if(this.drivers.containsKey(neo.getEmail())) throw new UserExistsException("User alredy exists");
    this.drivers.put(neo.getEmail(), neo);
  }

  public Taxi getClosestTaxi(){
    return this.taxis.first();
  }

  public Taxi getClosestCar(){
    Iterator<Taxi> it = this.getTaxis().iterator();
    Taxi t = null;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof Car) flag = 1;
    }
    return t;
  }

  public Taxi getClosestVan(){
    Iterator<Taxi> it = this.getTaxis().iterator();
    Taxi t = null;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof Van) flag = 1;
    }
    return t;
  }

  public Taxi getClosestMotorBike(){
    Iterator<Taxi> it = this.getTaxis().iterator();
    Taxi t = null;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof MotorBike) flag = 1;
    }
    return t;
  }

  public int getUserType(){
    return this.userType;
  }

  public void login(String email, String password) throws UserDoesNotExistsException{
    if(this.clients.containsKey(email) != false){
      this.userType = 1;
      if(!password.equals(this.clients.get(email).getPassword())) throw new UserDoesNotExistsException("Password incorrect");
    } else{
      if(this.drivers.containsKey(email) != false){
        this.userType = 2;
        if(!password.equals(this.drivers.get(email).getPassword())) throw new UserDoesNotExistsException("Password incorrect");
      }
      else throw new UserDoesNotExistsException("User not found");
    }
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

  public int getTraffic(Taxi t){
    int trafficCounter = 0;
    double distance;
    for(Taxi in : taxis){
      distance = t.getLocation().distanceTo(in.getLocation());
      if(distance <= 3 && distance != 0)
        trafficCounter++;
    }
    return trafficCounter;
  }

  public static void main(String[] args){
    new UMeRapp().run();
  }
}
