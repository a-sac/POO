import java.util.Queue;
/**
 * Escreva a descrição da classe Taxi aqui.
 *
 * @author tdaa
 * @version 20/04
 */
public class Taxi{
  private Driver driver;
  private Client client;
  private Point2D location;
  private Queue<Client> waitingQ;

  public Taxi(Taxi t){
    this.driver = t.driver;
    this.client = t.client;
    this.location = t.location;
    this.waitingQ = t.waitingQ;
  }

  public Taxi(Driver d, Client c, Point2D p, Queue<Client> q){
    this.driver = d;
    this.client = c;
    this.location = p;
    this.waitingQ = q;
  }

  public Driver getDriver(){
    return this.driver;
  }

  public Client getClient(){
    return this.client;
  }

  public Point2D getLocation(){
    return this.location;
  }

  public Taxi clone(){
    return new Taxi(this);
  }
}
