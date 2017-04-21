import java.util.Queue;
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
    this.client = t.getClient();
    this.trip = t.getTrip();
    this.location = t.getLocation();
    this.occupied = t.isOccupied();
    this.waitingQ = t.getWaitingQ();
    this.basePrice = t.getBasePrice();
  }

  public Taxi(Driver driver, Vehicle vehicle, Client client, TaxiRide trip, Point2D location, boolean occcupied, Queue<Client> q, double basePrice){
    this.driver = driver;
    this.vehicle = vehicle;
    this.client = client;
    this.trip = trip;
    this.location = location;
    this.occupied = occupied;
    this.waitingQ = q;
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

  public void enqueue(Client c){
    this.waitingQ.offer(c);
  }

  public void goToNextClient(){
    this.client = this.waitingQ.poll();
    Point2D clientLocation = new Point2D(this.client.getLocation());
    this.location.travelTo(clientLocation);
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
    TaxiRide newTrip = new TaxiRide(this.location, clientDestination, this, distance, expectedTime, actualTime, price);
    this.trip = newTrip;
    this.drive(clientDestination, distance);
  }

  public void drive(Point2D destination, double distance){
    this.location.travelTo(destination);
    this.driver.addKms(distance);
  }

  public void rideEnd(){
    this.chargeClient(this.client, this.trip.getPrice());
    this.clientLeaves();
  }

  public void clientLeaves(){
    this.client = null;
    this.trip = null;
    this.occupied = false;
  }
  public void chargeClient(Client c, double price){
    c.spendMoney(price);
  }

}
