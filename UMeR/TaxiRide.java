
/**
 * Write a description of class TaxiRide here.
 *
 * @author jhbb
 * @version 21/04
 */
public class TaxiRide{


  private Point2D start;
  private Point2D destination;
  private Taxi taxi;
  private double distance;
  private double expectedTime;
  private double actualTime;
  private double price;

  public TaxiRide(Point2D start, Point2D destination, Taxi taxi, double distance, double expectedTime, double actualTime, double price){
    this.start = start;
    this.destination = destination;
    this.taxi = taxi;
    this.distance = distance;
    this.expectedTime = expectedTime;
    this.actualTime = actualTime;
    this.price = price;
  }

  public TaxiRide(TaxiRide tr){
    this.start = tr.getStart();
    this.destination = tr.getDestination();
    this.taxi = tr.getTaxi();
    this.distance = tr.getDistance();
    this.expectedTime = tr.getExpectedTime();
    this.actualTime = tr.getActualTime();
    this.price = tr.getPrice();
  }

	public Point2D getStart() {
		return this.start;
	}

	public void setStart(Point2D start) {
		this.start = start;
	}

	public Point2D getDestination() {
		return this.destination;
	}

	public void setDestination(Point2D destination) {
		this.destination = destination;
	}

	public Taxi getTaxi() {
		return this.taxi;
	}

  public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getExpectedTime() {
		return this.expectedTime;
	}

	public void setExpectedTime(double expectedTime) {
		this.expectedTime = expectedTime;
	}

	public double getActualTime() {
		return this.actualTime;
	}

	public void setActualTime(double actualTime) {
		this.actualTime = actualTime;
	}

  public double getPrice(){
    return this.price;
  }
}
