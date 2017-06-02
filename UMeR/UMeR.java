import java.util.*;
import java.io.Serializable;
/**
 * Write a description of class UMeR here.
 *
 * @author jhbb
 * @version 02/05
 */
public class UMeR implements Serializable
{
  private int userType; // 1 is client; 2 is driver
  private int nVehicles;
  private int nDrivers;
  private TreeMap<String, Client> clients;
  private TreeMap<String, Vehicle> vehicles;
  private TreeMap<String, Driver> drivers;
  //private TreeMap<String, Driver> workingDrivers;
  private TreeSet<Taxi> taxis;
  private static int driverCode = 611;
  private double totalProfit;

  public UMeR(){
    this.clients = new TreeMap<String, Client>();
    this.vehicles = new TreeMap<String, Vehicle>();
    this.drivers = new TreeMap<String, Driver>();
    //this.workingDrivers = new TreeMap<String, Driver>();
    this.taxis = new TreeSet<Taxi>(new TaxiComparator());
    this.nVehicles = this.vehicles.size();
    this.nDrivers = this.drivers.size();
    this.totalProfit = 0;
  }

  /*Comparator<Taxi> TaxiComparator = new Comparator<Taxi>()
  {
      @Override
      public int compare(Taxi t1, Taxi t2){
        return t1.getVehicle().getPlate().compareTo(t2.getVehicle().getPlate());
      }
  };*/

  public int getUserType(){
    return this.userType;
  }

  public int getNVehicles(){
    return this.nVehicles;
  }

  public int getNDrivers(){
    return this.nDrivers;
  }

  public int getDriverCode(){
    return this.driverCode;
  }

  public double getTotalProfit(){
    return this.totalProfit;
  }

  public void setTotalProfit(double money){
    this.totalProfit += money;
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

  /*public Map<String, Driver> getWorkingDrivers() throws NoDriversException{
    if(this.workingDrivers.isEmpty()) throw new NoDriversException("No drivers in database");
    else{
      Map<String, Driver> neo = new TreeMap<String, Driver>();
      for(Map.Entry<String, Driver> entrys : this.workingDrivers.entrySet()){
        neo.put(entrys.getKey(), entrys.getValue());
      }
      return neo;
    }
  }*/

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

  /*public void addWorkingDriver(Driver neo) throws UserExistsException{
    if(this.workingDrivers.containsKey(neo.getEmail())) throw new UserExistsException("Motorista já existente");
    this.workingDrivers.put(neo.getEmail(), neo);
  }*/

  public void addTaxi(Driver d, Vehicle v){
    this.taxis.add(new Taxi(d, v));
  }

  public Taxi getSpecificTaxi(String plate){
    Iterator<Taxi> it = this.taxis.iterator();
    Boolean flag = false;
    Taxi t = null;
    while(it.hasNext() && !flag){
      t = it.next();
      if(t.getVehicle().getPlate().equals(plate)) flag = true;
    }
    return t;
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
    else System.out.println("Sem Taxis");
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
    if(it.hasNext() == false && t.getVehicle() instanceof Car) return t;
    else if(it.hasNext() == false && !(t.getVehicle() instanceof Car)) return null;
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
    if(it.hasNext() == false && t.getVehicle() instanceof Car) return t;
    else if(it.hasNext() == false && !(t.getVehicle() instanceof Car)) return null;
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
    if(it.hasNext() == false && t.getVehicle() instanceof Van) return t;
    else if(it.hasNext() == false && !(t.getVehicle() instanceof Van)) return null;
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
    if(it.hasNext() == false && t.getVehicle() instanceof Van) return t;
    else if(it.hasNext() == false && !(t.getVehicle() instanceof Van)) return null;
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
    if(it.hasNext() == false && t.getVehicle() instanceof MotorBike) return t;
    else if(it.hasNext() == false && !(t.getVehicle() instanceof MotorBike)) return null;
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
    if(it.hasNext() == false && t.getVehicle() instanceof MotorBike) return t;
    else if(it.hasNext() == false && !(t.getVehicle() instanceof MotorBike)) return null;
    while(it.hasNext()){
      t = it.next();
      if(t.getVehicle() instanceof MotorBike && t.isOccupied()==false){
        tmp = t.getLocation().distanceTo(c.getLocation());
        if(distance > tmp) distance = tmp;
      }
    }
    return t;
  }

  public boolean printTaxis(){
    boolean isEmpty = true;
    if(this.taxis.isEmpty()==false){
      isEmpty = false;
      for (Taxi t: this.taxis){
			 System.out.println(t.getVehicle().getPlate());
		  }
    }
    return isEmpty;
	}

  public void printDriver(Driver d){
    System.out.println(this.drivers.get(d.getEmail()).getName());
    System.out.println(this.drivers.get(d.getEmail()).getEmail());
    System.out.println(this.drivers.get(d.getEmail()).getAddress());
    System.out.println(this.drivers.get(d.getEmail()).getBirthday());
  }

  public void printClient(Client c){
    System.out.println(this.clients.get(c.getEmail()).getName());
    System.out.println(this.clients.get(c.getEmail()).getEmail());
    System.out.println(this.clients.get(c.getEmail()).getAddress());
    System.out.println(this.clients.get(c.getEmail()).getBirthday());
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
          if(t.getDriver().getEmail().equals(email)) flag=1;
        }
        if(flag==0) {
          System.out.println("E-mail inválido. Tente outra vez!");
          it = this.taxis.iterator();
        }
      }while(flag==0);
    }
    return email;
  }

  public String writePlate(){
    Scanner read = new Scanner(System.in);
    String plate=null, actual=null;
    if(this.taxis.isEmpty()==false){
      Iterator<Taxi> it = this.taxis.iterator();
      Taxi t;
      int flag=0;
      do{
        System.out.print("Escolha a matrícula do taxi pretendido: ");
        plate = read.nextLine();
        while(it.hasNext() && flag==0){
          t = it.next();
          actual = t.getVehicle().getPlate();
          if(actual.equals(plate)) flag=1;
        }
        if(flag==0){
          System.out.println("Matrícula inválida. Tente outra vez!");
          it = this.taxis.iterator();
        }
      }while(flag==0);
    }
    return plate;
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

  public boolean checkEmail(String email){
    if(!this.clients.isEmpty()){
      if(this.clients.containsKey(email)) return true;
      if(!this.drivers.isEmpty()) return (this.drivers.containsKey(email));
      return false;
    }
    if(!this.drivers.isEmpty()) return this.drivers.containsKey(email);
    return false;
  }

  public Taxi startDay(Driver d){
    int flag=0;
    Iterator<Taxi> it = this.taxis.iterator();
    Taxi t;
    while(it.hasNext() && flag==0){
      t = it.next();
      if(t.getDriver().getEmail().equals(d.getEmail()))
        flag = 1;
    }
    if(flag==1) {
      System.out.println("Já iniciou trabalho");
      return null;
    }
    t = new Taxi(d, this.vehicles.get(this.vehicles.firstKey()));
    this.taxis.add(t);
    this.vehicles.remove(this.vehicles.firstKey());
    setNDrivers(getNDrivers() - 1);
    /*try{addWorkingDriver(d);}
    catch(UserExistsException e){System.out.println("Motorista existente");}*/
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
        //this.drivers.replace(d.getEmail(), d);
        //this.workingDrivers.remove(d.getEmail());
        this.vehicles.put(t.getVehicle().getPlate(), t.getVehicle().clone());
        this.taxis.remove(t);
        flag=1;
      }
    }
  }

  public void updateClient(Client c){
    this.clients.replace(c.getEmail(), c);
  }

  public void updateDriver(Driver d){
    this.drivers.replace(d.getEmail(), d);
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
