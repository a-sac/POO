import java.util.*;
/**
 * Escreva a descrição da classe Taxi aqui.
 *
 * @author tdaa/jhbb
 * @version 21/04
 */
public class Taxi{
  private int taxiID;
  private Driver driver;
  private Vehicle vehicle;
  private Client client;
  private TaxiRide trip;
  private Point2D location;
  private boolean occupied;
  private Queue<Client> waitingQ;
  private double basePrice;

  public Taxi(Taxi t){
    this.taxiID = t.getID();
    this.driver = t.getDriver();
    this.vehicle = t.getVehicle();
    this.location = t.getLocation();
    this.occupied = t.isOccupied();
    this.waitingQ = t.getWaitingQ();
    this.basePrice = t.getBasePrice();
  }

  public Taxi(int id, Driver driver, Vehicle vehicle){
    Random coord = new Random();
    Point2D start = new Point2D(coord.nextInt(21)-10, coord.nextInt(21)-10);
    this.taxiID = id;
    this.driver = driver;
    this.vehicle = vehicle;
    this.location = start;
    this.occupied = false;
    this.waitingQ = new LinkedList<Client>();
    this.basePrice = 0.57;
  }

  public Taxi(int id, Driver driver, Vehicle vehicle, Point2D location, boolean occcupied,  double basePrice){
    this.taxiID = id;
    this.driver = driver;
    this.vehicle = vehicle;
    this.location = location;
    this.occupied = occupied;
    this.waitingQ = new LinkedList<Client>();
    this.basePrice = basePrice;
  }

  public int getID(){
    return this.taxiID;
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
    return "-- Taxi (" + this.taxiID + ") driven by " + this.driver.toString() + " with vehicle " + this.vehicle.toString() + ". If you check if it's occupied the awnser is " + this.occupied; 
  }

  public int compareTo(Taxi t){
    if(this.getDriver().getTrustFactor() > t.getDriver().getTrustFactor())
      return 1;
    if(this.getDriver().getTrustFactor() == t.getDriver().getTrustFactor())
      return 0;
    else return -1;
  }

  public void enqueue(Client c){
    this.waitingQ.offer(c.clone());
  }

  public void goToNextClient(){
    this.client = this.waitingQ.poll();
    Point2D clientLocation = new Point2D(this.client.getLocation());
    this.location.travelTo(clientLocation);
    this.pickUpClient();
  }

  public void pickUpClient(){
    this.occupied = true;
    this.rideStart();
  }

  public void rideStart(){
    double distance = this.location.distanceTo(this.client.getLocation());
    double expectedTime = distance/vehicle.getSpeed();
    double actualTime = expectedTime * vehicle.getFactor() * driver.getTrustFactor();
    double price;
    if(actualTime > 1.25*expectedTime) price = (this.basePrice * distance)/2;
    else price = this.basePrice * distance;
    Point2D clientDestination = new Point2D(this.client.getDestination());
    TaxiRide newTrip = new TaxiRide(this.location, clientDestination, this.vehicle, distance, expectedTime, actualTime, price);
    this.trip = newTrip;
    this.drive(clientDestination, distance);
  }

  public void drive(Point2D destination, double distance){
    this.location.travelTo(destination);
    this.driver.addKms(distance);
    this.rideEnd();
  }

  public void rideEnd(){
    this.chargeClient(this.client, this.trip.getPrice());
    this.clientLeaves();
  }

  public void chargeClient(Client c, double price){
    c.spendMoney(price);
  }

  public void clientLeaves(){
    this.client.addToHistory(this.driver.getEmail(), this.trip);
    this.client = null;
    this.trip = null;
    this.occupied = false;
  }


}
