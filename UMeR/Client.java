
/**
 * Write a description of class Client here.
 *
 * @author jhbb
 * @version 21/04
 */
public class Client{

  //instance variables
  private Point2D location;
  private Point2D destination;
  private String email;
  private String password;
  private String name;
  private String address;
  private String birthday;
  private double balance;
  //TODO TaxiRide list

  public Client(Point2D location, Point2D destination, String email, String password, String name, String address, String birthday, double balance){
    this.location = location;
    this.destination  = destination;
    this.email = email;
    this.password = password;
    this.name = name;
    this.address = address;
    this.birthday = birthday;
    this.balance = balance;
  }

  public Client(Client c){
    this.location = c.getLocation();
    this.destination = c.getDestination();
    this.email = c.getEmail();
    this.password = c.getPassword();
    this.name = c.getName();
    this.address = c.getAddress();
    this.birthday = c.getBirthday();
    this.balance = c.getBalance();
  }

  public Point2D getLocation(){
    return this.location;
  }

  public Point2D getDestination(){
    return this.destination;
  }

  public String getEmail(){
    return this.email;
  }

  public String getPassword(){
    return this.password;
  }

  public String getName(){
    return this.name;
  }

  public String getAddress(){
    return this.address;
  }

  public String getBirthday(){
    return this.birthday;
  }

  public double getBalance(){
    return this.balance;
  }

  public void spendMoney(double value){
    this.balance-=value;
  }
  public Client clone(){
    return new Client(this);
  }

  public void callTaxi(Taxi t){
    t.enqueue(this);
  }

}
