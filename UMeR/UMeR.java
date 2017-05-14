import java.util.*;
/**
 * Write a description of class UMeR here.
 *
 * @author jhbb
 * @version 02/05
 */
public class UMeR{
  private int userType; // 1 is client; 2 is driver
  private int nVehicles;
  private int nDrivers;
  private TreeMap<String, Client> clients;
  private TreeMap<String, Vehicle> vehicles;//vehicles with no driver
  private TreeMap<String, Driver> drivers;//drivers with no
  private TreeSet<Taxi> taxis;
  private static int driverCode = 611;

  public UMeR(){
    this.clients = new TreeMap<String, Client>();
    this.vehicles = new TreeMap<String, Vehicle>();
    this.drivers = new TreeMap<String, Driver>();
    this.taxis = new TreeSet<Taxi>(new TaxiComparator());
    this.nVehicles = this.vehicles.size();
    this.nDrivers = this.drivers.size();
  }

  public int getUserType(){
    return this.userType;
  }

  public int getNVehicles(){
    return this.vehicles.size();
  }

  public int getNDrivers(){
    return this.drivers.size();
  }

  public int getDriverCode(){
    return this.driverCode;
  }

  public void setNVehicles(int nvehicles){
    this.nVehicles = nvehicles;
  }

  public void setNDrivers(int ndrivers){
    this.nDrivers = ndrivers;
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

  public Map<String, Vehicle> getVehicles() throws NoVehiclesException{
    if(this.vehicles.isEmpty()) throw new NoVehiclesException("No vehicles in database");
    else{
      Map<String, Vehicle> neo = new TreeMap<String, Vehicle>();
      for(Map.Entry<String, Vehicle> entrys : this.vehicles.entrySet()){
        neo.put(entrys.getKey(), entrys.getValue());
      }
    return neo;
    }
  }

  public TreeSet<Taxi> getTaxis() throws NoTaxisException{
    if(this.taxis.isEmpty()) throw new NoTaxisException("Sem taxis");
    return this.taxis;
  }

  public void addClient(Client neo) throws UserExistsException{
    if(this.clients.containsKey(neo.getEmail())) throw new UserExistsException("Cliente já existente");
    this.clients.put(neo.getEmail(), neo);
  }


  public void addVehicle(Vehicle neo) throws VehicleExistsException{
    if(this.vehicles.containsKey(neo.getPlate())) throw new VehicleExistsException("Veículo já existente");
    this.vehicles.put(neo.getPlate(), neo);
  }

  public void addDriver(Driver neo) throws UserExistsException{
    if(this.drivers.containsKey(neo.getEmail())) throw new UserExistsException("Motorista já existente");
    this.drivers.put(neo.getEmail(), neo);
  }

  public void addTaxi(Driver d, Vehicle v){
    this.taxis.add(new Taxi(d, v));
  }

  public Taxi getClosestTaxi(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    if(it.hasNext()){
      t = it.next();
      distance = t.getLocation().distanceTo(c.getLocation());
    }
    while(it.hasNext()){
      t = it.next();
      tmp = t.getLocation().distanceTo(c.getLocation());
      if(distance > tmp) distance = tmp;
    }
    return t;
  }

  public Taxi getClosestFreeTaxi(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.isOccupied()==false){
        flag=1;
        distance = t.getLocation().distanceTo(c.getLocation());
      }
    }
    if(it==null) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.isOccupied()==false){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public Taxi getClosestCar(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof Car){
        flag=1;
        distance = t.getLocation().distanceTo(c.getLocation());
      }
    }
    if(it==null) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.getVehicle() instanceof Car){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public Taxi getClosestFreeCar(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof Car && t.isOccupied()==false){
        flag=1;
        distance = t.getLocation().distanceTo(c.getLocation());
      }
    }
    if(it==null) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.getVehicle() instanceof Car && t.isOccupied()==false){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public Taxi getClosestVan(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof Van){
        flag=1;
        distance = t.getLocation().distanceTo(c.getLocation());
      }
    }
    if(it==null) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.getVehicle() instanceof Van){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public Taxi getClosestFreeVan(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof Van && t.isOccupied()==false){
        flag=1;
        distance = t.getLocation().distanceTo(c.getLocation());
      }
    }
    if(it==null) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.getVehicle() instanceof Van && t.isOccupied()==false){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public Taxi getClosestMotorBike(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof MotorBike){
        flag=1;
        distance = t.getLocation().distanceTo(c.getLocation());
      }
    }
    if(it==null) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.getVehicle() instanceof MotorBike){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public Taxi getClosestFreeMotorBike(Client c){
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t = null;
    double distance=0.0 , tmp=0.0;
    int flag=0;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getVehicle() instanceof MotorBike && t.isOccupied()==false){
        flag=1;
        distance = t.getLocation().distanceTo(c.getLocation());
      }
    }
    if(it==null) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.getVehicle() instanceof MotorBike && t.isOccupied()==false){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public boolean printDrivers(){
    boolean bool=false;
    if(this.taxis.isEmpty()==false){
      bool = true;
      for (Taxi t: this.taxis){
			 System.out.println(t.getDriver().getEmail());
		  }
    }
    return bool;
	}

  public String writeEmail(){
    Scanner read = new Scanner(System.in);
    String email=null, actual=null;
    if(this.taxis.isEmpty()==false){
      Iterator<Taxi> it = this.taxis.iterator();
      Taxi t;
      int flag=0;
      do{
        System.out.print("Escolha o e-mail do motorista pretendido: ");
        email = read.nextLine();
        while(it.hasNext() && flag==0){
          t = it.next();
          actual = t.getDriver().getEmail();
          if(actual.equals(email)==false) System.out.println("E-mail inválido. Tente outra vez!");
          else flag=1;
        }
      }while(email.equals(actual)==false);
    }
    return email;
  }

  public void login(String email, String password) throws UserDoesNotExistsException{
    if(this.clients.containsKey(email) != false){
      this.userType = 1;
      if(!password.equals(this.clients.get(email).getPassword())) throw new UserDoesNotExistsException("Password incorresta");
    } else{
      if(this.drivers.containsKey(email) != false){
        this.userType = 2;
        if(!password.equals(this.drivers.get(email).getPassword())) throw new UserDoesNotExistsException("Password incorreta");
      }
      else throw new UserDoesNotExistsException("Utilizador não encontrado");
    }
  }

  public Taxi startDay(Driver d){
    this.taxis = new TreeSet<Taxi>(new TaxiComparator());
    Taxi t = new Taxi(d, this.vehicles.get(this.vehicles.firstKey()));
    this.taxis.add(t);
    this.vehicles.remove(this.vehicles.firstKey());
    this.drivers.remove(d.getEmail());
    System.out.println("Tenha um bom dia de trabalho " + d.getName());
    return t;
  }

  public void endDay(Driver d){
    int flag=0;
    Taxi t = null;
    Iterator<Taxi> it = this.taxis.iterator();
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getDriver().getEmail().equals(d.getEmail())){
        this.drivers.put(d.getEmail(), d);
        this.vehicles.put(t.getVehicle().getPlate(), t.getVehicle().clone());
        this.taxis.remove(t);
        flag=1;
      }
    }
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
