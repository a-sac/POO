import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Escreva a descrição da classe Taxi aqui.
 *
 * @author tdaa/jhbb
 * @version 21/04
 */
public class Taxi{
  private Driver driver;
  private Vehicle vehicle;
  private Client client;
  private TaxiRide trip;
  private Point2D location;
  private boolean occupied;
  private Queue<Client> waitingQ;
  private double basePrice;

  public Taxi(Taxi t){
    this.driver = t.getDriver();
    this.vehicle = t.getVehicle();
    this.location = t.getLocation();
    this.occupied = t.isOccupied();
    this.waitingQ = t.getWaitingQ();
    this.basePrice = t.getBasePrice();
  }

  public Taxi(Driver driver, Vehicle vehicle){
    Random coord = new Random();
    Point2D start = new Point2D(coord.nextInt(21)-10, coord.nextInt(21)-10);
    this.driver = driver;
    this.vehicle = vehicle;
    this.location = start;
    this.occupied = false;
    this.waitingQ = new LinkedList<Client>();
    this.basePrice = 0.57;
  }

  public Taxi(Driver driver, Vehicle vehicle, Point2D location, boolean occcupied,  double basePrice){
    this.driver = driver;
    this.vehicle = vehicle;
    this.location = location;
    this.occupied = occupied;
    this.waitingQ = new LinkedList<Client>();
    this.basePrice = basePrice;
  }

  public Driver getDriver(){
    return this.driver;
  }

  public Vehicle getVehicle(){
    return this.vehicle;
  }

  public Client getClient(){
    return this.client;
  }

  public Point2D getLocation(){
    return this.location;
  }

  public Queue<Client> getWaitingQ(){
    return this.waitingQ;
  }

  public boolean isOccupied(){
    return this.occupied;
  }

  public TaxiRide getTrip(){
    return this.trip;
  }

  public double getBasePrice(){
    return this.basePrice;
  }

  public Taxi clone(){
    return new Taxi(this);
  }

  public String toString(){
    return "-- Taxi (" + this.vehicle.getPlate() + ") conduzido por " + this.driver.toString() + ". com o veículo " + this.vehicle.toString() + ".\nLocalização: " + this.location.toString() + "\n Ocupado? " + this.occupiedToString();
  }

  public int compareTo(Taxi t){
    if(this.getDriver().getTrustFactor() > t.getDriver().getTrustFactor())
      return 1;
    if(this.getDriver().getTrustFactor() == t.getDriver().getTrustFactor())
      return 0;
    else return -1;
  }

  public void enqueue(Client c){
    this.waitingQ.add(c);
  }

  public void goToNextClient(){
    this.client = this.waitingQ.poll();
    Point2D clientLocation = new Point2D(this.client.getLocation());
    this.location.travelTo(clientLocation);
  }

  public void pickUpClient(){
    this.occupied = true;
  }

  public void rideStart(int trafficCounter){
    ThreadLocalRandom weather = ThreadLocalRandom.current();
    if(this.client!=null){
      double distance = this.location.distanceTo(this.client.getDestination());
      double expectedTime = distance/vehicle.getSpeed();
      double actualTime = expectedTime * vehicle.getFactor() * driver.getTrustFactor() * weather.nextDouble(0.5, 2.5) * (double) trafficCounter/2.0;
      double price;
      if(actualTime > 1.25*expectedTime){
        price = (this.basePrice * distance)/2;
        this.getDriver().addTimeLost(actualTime - expectedTime);
      }
      else price = this.basePrice * distance;
      Point2D clientDestination = new Point2D(this.client.getDestination());
      TaxiRide newTrip = new TaxiRide(this.location.clone(), clientDestination, this.driver.getEmail(), this.client.getEmail(), this.vehicle, distance, expectedTime, actualTime, price);
      this.trip = newTrip;
      this.drive(clientDestination, distance);
    }
    else System.out.println("Sem Cliente");
  }

  public void drive(Point2D destination, double distance){
    this.location.travelTo(destination);
    this.driver.addKms(distance);
  }

  public void rideEnd(){
    if(this.client!=null){
      this.chargeClient(this.client, this.trip.getPrice());
      this.clientLeaves();
    }
    else System.out.println("Sem Cliente");
  }

  public void chargeClient(Client c, double price){
    c.spendMoney(price);
  }

  public void clientLeaves(){
    System.out.println(this.trip.toString());
    this.client.addToHistory(new Date(), this.trip);
    this.client = null;
    this.trip = null;
    this.occupied = false;
  }

  public String occupiedToString(){
    String occupied;
    if(this.occupied==true) occupied = "Sim";
    else occupied = "Não";
    return occupied;
  }
}
