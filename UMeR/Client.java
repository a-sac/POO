
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

  public void spendMoney(double value){
    this.balance-=value;
  }

  public void callTaxi(Taxi t){
    t.enqueue(this);
  }

  public Client clone(){
    return new Client(this);
  }

}
